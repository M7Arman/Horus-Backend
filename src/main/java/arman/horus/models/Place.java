package arman.horus.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author arman
 */
public class Place {

    @JsonProperty(value = "title", required = true)
    private String title;
    @JsonProperty(value = "images", required = true)
    private String[] images;
    @JsonProperty(value = "description", required = true)
    private String description;
    @JsonProperty(value = "address", required = true)
    private Address address;
    @JsonProperty(value = "rank", required = true)
    private int rank;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
