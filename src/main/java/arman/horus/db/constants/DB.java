package arman.horus.db.constants;

/**
 *
 * @author arman
 */
public class DB {

    public static class Collection {
        public final static String PLACES = "places";
        public final static String TRIPS = "trips";
    }
    
    public static class Key {
        public final static String ID = "_id";
        public final static String RANK = "rank";
        public final static String TITLE = "title";
        public final static String NAME = "name";
        public final static String TIME = "time";
        public final static String CREATED_TIME = "created_time";
        public final static String IMAGES = "images";
    }
    
    public static class KeysToExclude {
        public final static String[] PLACES = {"description", "address", "rank"};
        public final static String[] TRIPS = {"description", "from", "target", "rank"};
    }
}
