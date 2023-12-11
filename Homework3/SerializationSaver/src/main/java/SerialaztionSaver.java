

import java.io.*;
import java.util.UUID;


public class SerialaztionSaver {


    public static String save(Serializable serializable) {

        String uuid = UUID.randomUUID().toString();
        String filename = serializable.getClass().getName()+"_"+ uuid;
        try (FileOutputStream fileOutputStream = new FileOutputStream(filename)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(serializable);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return uuid;
    }

}
