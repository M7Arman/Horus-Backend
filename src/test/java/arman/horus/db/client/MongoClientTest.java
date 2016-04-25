package arman.horus.db.client;

import arman.horus.db.clients.MongoDBClient;
import arman.horus.db.interfaces.IDbClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author arman
 */
public class MongoClientTest {

    private ObjectMapper mapper;
    private IDbClient client;

    @BeforeClass
    public void setUp() {
        mapper = new ObjectMapper();
        client = MongoDBClient.getInstance();
    }

    @Test
    public void getAllTripsTest() {
        final String trips = client.getAllTrips();
        System.out.println("\nAll trips: \n" + trips);
        Assert.assertNotNull(trips, "Failed to get all trips");
    }

    @Test
    public void getAllPlacesTest() {
        final String places = client.getAllPlaces();
        System.out.println("\nAll places: \n" + places);
        Assert.assertNotNull(places, "Failed to get all places");
    }

    @Test
    public void getPopularTripsTest() {
        final String trips = client.getPopularTrips();
        System.out.println("\nPopular trips: \n" + trips);
        Assert.assertNotNull(trips, "Failed to get popular trips");
    }

    @Test
    public void getPopularPlacesTest() {
        final String places = client.getPopularPlaces();
        System.out.println("\nPopular places: \n" + places);
        Assert.assertNotNull(places, "Failed to get popular places");
    }

    @Test
    public void getTripDetailTest() {
        final String tripId = "570f9df7002ed036eba0fa19";
        final String trip = client.getTripDetail(tripId);
        System.out.println("\ntrip: \n" + trip);
        Assert.assertNotNull(trip, "Failed to get trip with " + tripId + " id");
    }

    @Test
    public void getPlaceDetailTest() {
        final String placeId = "570fd433002ed0578c3eed2a";
        final String place = client.getPlaceDetail(placeId);
        System.out.println("\nplace: \n" + place);
        Assert.assertNotNull(place, "Failed to get place with " + placeId + " id");
    }

    // helper methods
    private String dummyTripJson() {
        return "{\"title\": \"Արշավ դեպի Մայմեխ\","
                + "\"description\": \"Արշավ դեպի Մայմեխ, որը կտևի 1 ամբողջ օր։\","
                + "\"images\": [],"
                + "\"rank\": 5,"
                + "\"from\": {\"display_name\" : \"Սարյանի պուրակ\",\"coord\" : [ -73413, 400266 ]},"
                + "\"target\": {\"display_name\" : \"Մայմեխի գագաթ\",\"coord\" : [ -12561, 48996 ]}"
                + "}";
    }

    private String dummyPlaceJson() {
        return "{\"name\": \"Պարզ լիճ\","
                + "\"description\": \"Հոսք ունեցող լիճ է։ Սնվում է աղբյուրներից։ Ունի 300մ երկարություն և 100մ լայնություն։ Միջին խորությունը 3 մ է, առավելագույնը 10 մ։ Մակերեսը կազմում է 0.027 կմ², ծավալը 83.8 հազար խորանարդ մետր։ Լիճն ունի սողանքաարգելափակոցային ծագում։ Զարգացած են սողանքային երևույթները։ Շրջապատված է անտառներով։\","
                + "\"images\": [],"
                + "\"rank\": 6,"
                + "\"address\": {\"display_name\" : \"Տավուշի մարզ, Դիլիջանից 9կմ հս-արլ.\",\"coord\" : [-73.9557513,40.7720166]}"
                + "}";
    }
}
