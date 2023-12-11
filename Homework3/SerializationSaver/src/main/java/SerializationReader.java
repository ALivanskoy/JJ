import java.io.*;

public class SerializationReader<T> {


    public T read (String filename) {

        try(FileInputStream fileInputStream = new FileInputStream(filename)) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (T) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }




}
