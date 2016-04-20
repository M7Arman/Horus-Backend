package arman.horus.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author arman
 */
public class Address {
    @JsonProperty(value = "display_name", required = true)
    private String displayName;

    @JsonProperty(value = "coord", required = true)
    private double[] coord;
}
