package arman.horus.db.interfaces;

import arman.horus.models.Place;
import arman.horus.models.Trip;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 *
 * @author arman
 */
public interface IDbClient {

    String getAllTrips();

    String getAllPlaces();

    String getPopularTrips();

    String getPopularPlaces();

    String getTripDetail(String id);

    String getPlaceDetail(String id);

    void createTrip(Trip trip) throws JsonProcessingException;

    void createPlace(Place place) throws JsonProcessingException;

    long deleteTrip(String id);

    long deletePlace(String id);

    String getTripsLocations();

    String getPlacesLocations();

}
