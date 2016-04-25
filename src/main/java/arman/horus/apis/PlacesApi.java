package arman.horus.apis;

import arman.horus.db.clients.MongoDBClient;
import arman.horus.db.interfaces.IDbClient;
import arman.horus.models.Place;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Variant;

@Path("places")
@Produces(MediaType.APPLICATION_JSON)
public class PlacesApi {
    
    public static IDbClient dbClient = MongoDBClient.getInstance();

    @GET
    public String getPlaces() {
        return dbClient.getAllPlaces();
    }
    
    @GET
    @Path("popular")
    public String getPopularPlaces() {
        return dbClient.getPopularPlaces();
    }
    
    @GET
    @Path("{placeId}")
    @Consumes(MediaType.TEXT_PLAIN)
    public String getTrip(@PathParam("placeId") String placeId) {
        return dbClient.getTripDetail(placeId);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTrip(Place place) {
        try {
            dbClient.createPlace(place);
            return Response.ok().build();
        } catch (JsonProcessingException ex) {
            Logger.getLogger(PlacesApi.class.getName()).log(Level.SEVERE, null, ex);
            // TODO: test this
            List<Variant> variants = new ArrayList<>();
            Variant e = new Variant(MediaType.WILDCARD_TYPE, Locale.ENGLISH, "UTF-8");
            variants.add(e);
            return Response.notAcceptable(variants).entity(ex.getMessage()).build();
        }
    }
}
