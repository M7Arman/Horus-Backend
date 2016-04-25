package arman.horus.apis;

import arman.horus.models.User;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("user")
public class UserApi {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser() {
        return new User("Arman", "Kostandyan", 21);
    }
}
