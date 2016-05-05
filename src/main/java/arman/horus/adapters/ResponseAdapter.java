package arman.horus.adapters;

import arman.horus.db.constants.DB;
import com.mongodb.BasicDBList;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.util.JSON;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author arman
 */
public class ResponseAdapter {
    
    public static String forItems(FindIterable<Document> input) {
        BasicDBList list = new BasicDBList();
        try (MongoCursor<Document> it = input.iterator()) {
            while (it.hasNext()) {
                Document next = it.next();
                updateImg(next);
                list.add(next);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.serialize(list);
    }

    private static void updateImg(Document next) {
        List<String> images = next.get(DB.Key.IMAGES, ArrayList.class);
        if(images == null || images.isEmpty()) {
            next.remove(DB.Key.IMAGES);
            return;
        }
        next.remove(DB.Key.IMAGES);
        next.append("image", images.get(0));
    }
    
}
