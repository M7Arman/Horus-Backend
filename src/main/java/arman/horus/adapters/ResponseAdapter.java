package arman.horus.adapters;

import arman.horus.db.constants.API;
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
                updateId(next);
                list.add(next);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.serialize(list);
    }

    private static void updateImg(Document doc) {
        List<String> images = doc.get(DB.Key.IMAGES, ArrayList.class);
        if(images == null || images.isEmpty()) {
            doc.remove(DB.Key.IMAGES);
            return;
        }
        doc.remove(DB.Key.IMAGES);
        doc.append(API.Key.IMAGE, images.get(0));
    }

    private static void updateId(Document doc) {
        String id = doc.getObjectId(DB.Key.ID).toString();
        doc.remove(DB.Key.ID);
        doc.append(API.Key.ID, id);
    }
    
}
