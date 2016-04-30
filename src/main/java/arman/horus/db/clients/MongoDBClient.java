package arman.horus.db.clients;

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
import com.mongodb.BasicDBList;
import com.mongodb.client.MongoCursor;
import com.mongodb.util.JSON;
import static com.mongodb.client.model.Filters.eq;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import static com.mongodb.client.model.Filters.exists;
import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.client.model.Projections.exclude;

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
        if(instance == null)
            instance = new MongoDBClient(dbHost);
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
        return toJsonArray(col.find().projection(keysToExclude));
    }

    @Override
    public String getAllPlaces() {
        MongoCollection<Document> col = db.getCollection(DB.Collection.PLACES);
        Bson keysToExclude = exclude(DB.KeysToExclude.PLACES);
        return toJsonArray(col.find().projection(keysToExclude));
    }

    @Override
    public String getPopularTrips() {
        MongoCollection<Document> col = db.getCollection(DB.Collection.TRIPS);
        FindIterable<Document> popularItems = getPopularItems(col);
        Bson keysToExclude = exclude(DB.KeysToExclude.TRIPS);
        return toJsonArray(popularItems.projection(keysToExclude));
    }

    @Override
    public String getPopularPlaces() {
        MongoCollection<Document> collection = db.getCollection(DB.Collection.PLACES);
        FindIterable<Document> popularItems = getPopularItems(collection);
        Bson keysToExclude = exclude(DB.KeysToExclude.PLACES);
        return toJsonArray(popularItems.projection(keysToExclude));
    }

    @Override
    public String getTripDetail(String id) {
        MongoCollection<Document> col = db.getCollection(DB.Collection.TRIPS);
        Document doc = getDocumentById(col, id);
        return doc.toJson();
    }

    @Override
    public String getPlaceDetail(String id) {
        MongoCollection<Document> col = db.getCollection(DB.Collection.PLACES);
        Document doc = getDocumentById(col, id);
        return doc.toJson();
    }

    @Override
    public void createTrip(Trip trip) throws JsonProcessingException {
        MongoCollection<Document> col = db.getCollection(DB.Collection.TRIPS);
        String tripJson = MAPPER.writeValueAsString(trip);
        Document td = Document.parse(tripJson);
        col.insertOne(td);
    }

    @Override
    public void createPlace(Place place) throws JsonProcessingException {
        MongoCollection<Document> col = db.getCollection(DB.Collection.PLACES);
        String placeJson = MAPPER.writeValueAsString(place);
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

    private String toJsonArray(FindIterable<Document> documents) {
        BasicDBList list;
        try (MongoCursor<Document> iterator = documents.iterator()) {
            list = new BasicDBList();
            while (iterator.hasNext()) {
                Document next = iterator.next();
                list.add(next);
            }
        }
        return JSON.serialize(list);
    }

    // to test
    public static class PrintBlock implements Block<Document> {

        @Override
        public void apply(Document t) {
            System.out.println("Document: " + t.toJson());
        }
    }
}
