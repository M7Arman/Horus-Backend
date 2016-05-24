package arman.horus.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author arman
 */
public class Address {

    @JsonProperty(value = "display_name", required = true)
    public String displayName;

    @JsonProperty(value = "coord", required = true)
    public double[] coord;

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[ ").append(displayName).append(": ");
        str.append("{ ")
                .append("latitude: ").append(coord[0])
                .append(", longitude: ").append(coord[1])
                .append("} ]");

        return str.toString();
    }

}
