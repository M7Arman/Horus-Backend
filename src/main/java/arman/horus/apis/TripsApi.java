package arman.horus.apis;

import arman.horus.db.clients.MongoDBClient;
import arman.horus.db.interfaces.IDbClient;
import arman.horus.models.Trip;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("trips")
public class TripsApi {

    private static final String IMAGES_PATH = "src/main/resources/images/trips/";
    private static final IDbClient dbClient = MongoDBClient.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getTrips() {
        return dbClient.getAllTrips();
    }

    @GET
    @Path("popular")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPopularTrips() {
        return dbClient.getPopularTrips();
    }

    @GET
    @Path("{tripId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public String getTrip(@PathParam("tripId") String tripId) {
        return dbClient.getTripDetail(tripId);
    }

    @GET
    @Path("image/{image}")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response getTripImage(@PathParam("image") String imageName) {
        File image = new File(IMAGES_PATH + imageName);
        if (image.exists()) {
            String mt = new MimetypesFileTypeMap().getContentType(image);
            return Response.ok(image, mt).build();
        }
        return Response
                .status(Status.BAD_REQUEST)
                .entity("Image not found!")
                .type(MediaType.TEXT_PLAIN)
                .build();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTrip(Trip trip) {
        try {
            dbClient.createTrip(trip);
            return Response.ok().build();
        } catch (JsonProcessingException ex) {
            Logger.getLogger(TripsApi.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
}
