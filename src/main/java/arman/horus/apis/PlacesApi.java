package arman.horus.apis;

import arman.horus.db.clients.MongoDBClient;
import arman.horus.db.interfaces.IDbClient;
import arman.horus.models.Place;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("places")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class PlacesApi {

    private static final String IMAGES_PATH = "src/main/resources/images/places/";
    private static final IDbClient dbClient = MongoDBClient.getInstance();

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
    public String getPlace(@PathParam("placeId") String placeId) {
        return dbClient.getPlaceDetail(placeId);
    }

    @DELETE
    @Path("{placeId}")
    @Consumes(MediaType.TEXT_PLAIN)
    public String deletePlace(@PathParam("placeId") String id) {
        long deletedPlacesCount = dbClient.deletePlace(id);
        String response = "{\"message\": \"%s\"}";
        if (deletedPlacesCount == 0) {
            return String.format(response, "There is no place with " + id + " ID");
        }
        return String.format(response, "The place with " + id + " ID was deleted");
    }

    @GET
    @Path("image/{image}")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response getPlaceImage(@PathParam("image") String imageName) {
        File image = new File(IMAGES_PATH + imageName);
        if (image.exists()) {
            String mt = new MimetypesFileTypeMap().getContentType(image);
            return Response.ok(image, mt).build();
        }
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity("Image not found!")
                .type(MediaType.TEXT_PLAIN)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPlace(Place place) {
        try {
            dbClient.createPlace(place);
            return Response.ok().build();
        } catch (JsonProcessingException ex) {
            Logger.getLogger(PlacesApi.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
}
