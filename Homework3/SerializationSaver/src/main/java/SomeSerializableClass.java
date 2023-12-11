import java.io.Serializable;
import java.util.UUID;

public class SomeSerializableClass implements Serializable {

    private String data;

    public SomeSerializableClass(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SomeSerializableClass{" +
                "data='" + data + '\'' +
                '}';
    }
}
