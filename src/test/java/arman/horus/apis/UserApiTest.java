package arman.horus.apis;

import arman.horus.HorusApp;
import arman.horus.models.User;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.grizzly.http.server.HttpServer;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserApiTest {

    private HttpServer server;
    private WebTarget target;

    @BeforeClass
    public void setUp() throws Exception {
        server = HorusApp.startServer();
        Client client = ClientBuilder.newClient();
        target = client.target(HorusApp.BASE_URI);
    }

    @AfterClass
    public void tearDown() throws Exception {
        server.shutdownNow();
    }

    @Test
    public void testUser() {
        WebTarget a = target.path("user");
        System.out.println("a.getUri().getPath(): " + a.getUri().getPath());
        User user = target.path("user").request(MediaType.APPLICATION_JSON).get(User.class);
        System.out.println("user: " + user);
        Assert.assertNotEquals(user.firstName, "", "The user doesn't have first name");
    }
}
