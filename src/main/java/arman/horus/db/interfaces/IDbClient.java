package arman.horus.db.interfaces;

import arman.horus.models.Place;
import arman.horus.models.Trip;
import com.fasterxml.jackson.core.JsonProcessingException;


/**
 *
 * @author arman
 */
public interface IDbClient {

    public String getAllTrips();

    public String getAllPlaces();

    public String getPopularTrips();

    public String getPopularPlaces();
    
    public String getTripDetail(String id);
    
    public String getPlaceDetail(String id);

    public void createTrip(Trip trip) throws JsonProcessingException;

    public void createPlace(Place place) throws JsonProcessingException;

}
