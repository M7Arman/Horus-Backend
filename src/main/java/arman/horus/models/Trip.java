package arman.horus.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author arman
 */
public class Trip {

    @JsonProperty(value = "title", required = true)
    private String title;
    @JsonProperty(value = "description", required = true)
    private String description;
    @JsonProperty(value = "images", required = true)
    private String[] images;
    @JsonProperty(value = "from", required = true)
    private Address from;
    @JsonProperty(value = "target", required = true)
    private Address target;
    @JsonProperty(value = "rank", required = true)
    private int rank;
    @JsonProperty(value = "time", required = true)
    private String time;

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

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public Address getFrom() {
        return from;
    }

    public void setFrom(Address from) {
        this.from = from;
    }

    public Address getTarget() {
        return target;
    }

    public void setTarget(Address target) {
        this.target = target;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
