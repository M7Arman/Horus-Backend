package arman.horus.db.clients;

import arman.horus.adapters.ResponseAdapter;
import arman.horus.db.constants.DB;
import arman.horus.db.interfaces.IDbClient;
import arman.horus.models.Place;
import arman.horus.models.Trip;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Filters.exists;
import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.client.model.Projections.exclude;
import static com.mongodb.client.model.Projections.include;

/**
 *
 * @author arman
 */
public class MongoDBClient implements IDbClient {

    private final static String DB_NAME = "horusdb";
    private final static int POPULARS_COUNT = 10;
    private final MongoDatabase db;
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static MongoDBClient instance = null;

    public static MongoDBClient getInstance() {
        final String dbHost = System.getProperty("db_host", "localhost");
        if (instance == null) {
            instance = new MongoDBClient(dbHost);
        }
        return instance;
    }

    private MongoDBClient() {
        MongoClient mongoClient = new MongoClient();
        db = mongoClient.getDatabase(DB_NAME);
    }

    private MongoDBClient(String host) {
        MongoClient mongoClient = new MongoClient(host);
        db = mongoClient.getDatabase(DB_NAME);
    }

    @Override
    public String getAllTrips() {
        MongoCollection<Document> col = db.getCollection(DB.Collection.TRIPS);
        Bson keysToExclude = exclude(DB.KeysToExclude.TRIPS);
        return ResponseAdapter.forItems(col.find().projection(keysToExclude));
    }

    @Override
    public String getAllPlaces() {
        MongoCollection<Document> col = db.getCollection(DB.Collection.PLACES);
        Bson keysToExclude = exclude(DB.KeysToExclude.PLACES);
        return ResponseAdapter.forItems(col.find().projection(keysToExclude));
    }

    @Override
    public String getPopularTrips() {
        MongoCollection<Document> col = db.getCollection(DB.Collection.TRIPS);
        FindIterable<Document> popularItems = getPopularItems(col);
        Bson keysToExclude = exclude(DB.KeysToExclude.TRIPS);
        return ResponseAdapter.forItems(popularItems.projection(keysToExclude));
    }

    @Override
    public String getTripsLocations() {
        MongoCollection<Document> col = db.getCollection(DB.Collection.TRIPS);
        Bson keysToInclude = include(DB.Key.TARGET, DB.Key.TITLE);
        FindIterable<Document> result = col.find().projection(keysToInclude);
        return ResponseAdapter.forItems(result);
    }

    @Override
    public String getPlacesLocations() {
        MongoCollection<Document> col = db.getCollection(DB.Collection.PLACES);
        Bson keysToInclude = include(DB.Key.ADDRESS, DB.Key.TITLE);
        FindIterable<Document> result = col.find().projection(keysToInclude);
        return ResponseAdapter.forItems(result);
    }

    @Override
    public String getPopularPlaces() {
        MongoCollection<Document> collection = db.getCollection(DB.Collection.PLACES);
        FindIterable<Document> popularItems = getPopularItems(collection);
        Bson keysToExclude = exclude(DB.KeysToExclude.PLACES);
        return ResponseAdapter.forItems(popularItems.projection(keysToExclude));
    }

    @Override
    public String getTripDetail(String id) {
        MongoCollection<Document> col = db.getCollection(DB.Collection.TRIPS);
        Document doc = getDocumentById(col, id);
        return ResponseAdapter.forTrip(doc);
    }

    @Override
    public String getPlaceDetail(String id) {
        MongoCollection<Document> col = db.getCollection(DB.Collection.PLACES);
        Document doc = getDocumentById(col, id);
        return ResponseAdapter.forPlace(doc);
    }

    @Override
    public long deleteTrip(String id) {
        MongoCollection<Document> col = db.getCollection(DB.Collection.TRIPS);
        Bson filter = eq(DB.Key.ID, new ObjectId(id));
        DeleteResult result = col.deleteOne(filter);
        return result.getDeletedCount();
    }

    @Override
    public long deletePlace(String id) {
        MongoCollection<Document> col = db.getCollection(DB.Collection.PLACES);
        Bson filter = eq(DB.Key.ID, new ObjectId(id));
        DeleteResult result = col.deleteOne(filter);
        return result.getDeletedCount();
    }

    @Override
    public void createTrip(Trip trip) throws JsonProcessingException {
        MongoCollection<Document> col = db.getCollection(DB.Collection.TRIPS);
        String tripJson = MAPPER.writeValueAsString(trip);
        System.out.println("[CREATE TRIP] " + tripJson);
        Document td = Document.parse(tripJson);
        col.insertOne(td);
    }

    @Override
    public void createPlace(Place place) throws JsonProcessingException {
        MongoCollection<Document> col = db.getCollection(DB.Collection.PLACES);
        String placeJson = MAPPER.writeValueAsString(place);
        System.out.println("[CREATE PLACE] " + placeJson);
        Document td = Document.parse(placeJson);
        col.insertOne(td);
    }

    // Helper methods
    private FindIterable<Document> getPopularItems(MongoCollection<Document> collection) {
        FindIterable<Document> documents = collection
                .find(exists(DB.Key.RANK))
                .sort(descending(DB.Key.RANK));
        return documents.limit(POPULARS_COUNT);
    }

    private Document getDocumentById(MongoCollection<Document> col, String id) {
        Bson filter = eq(DB.Key.ID, new ObjectId(id));
        return col.find(filter).first();
    }

    // to test
    public static class PrintBlock implements Block<Document> {

        @Override
        public void apply(Document t) {
            System.out.println("Document: " + t.toJson());
        }
    }
}
