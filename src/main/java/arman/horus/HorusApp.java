package arman.horus;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.ext.ContextResolver;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;

/**
 * Main class.
 *
 */
public class HorusApp {

    public static final String BASE_URI = "http://localhost:8080/myapp/";
    public static final String RESOURCES_PACKAGE = "arman.horus.apis";

    /**
     * Main method.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.shutdownNow();
    }

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this
     * application.
     *
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig()
                .packages(RESOURCES_PACKAGE)
                .register(createMoxyJsonResolver());

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static ContextResolver<MoxyJsonConfig> createMoxyJsonResolver() {
        final MoxyJsonConfig moxyJsonConfig = new MoxyJsonConfig();
        Map<String, String> namespacePrefixMapper = new HashMap<>(1);
        namespacePrefixMapper.put("http://www.w3.org/2001/XMLSchema-instance", "xsi");
        moxyJsonConfig.setNamespacePrefixMapper(namespacePrefixMapper).setNamespaceSeparator(':');
        return moxyJsonConfig.resolver();
    }
}
