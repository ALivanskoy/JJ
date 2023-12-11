/**
 * Написать класс с двумя методами:
 * 1. принимает объекты, имплементирующие интерфейс serializable, и сохраняющие их в файл. Название файл - class.getName() + "_" + UUID.randomUUID().toString()
 * 2. принимает строку вида class.getName() + "_" + UUID.randomUUID().toString() и загружает объект из файла и удаляет этот файл.
 * <p>
 * Что делать в ситуациях, когда файла нет или в нем лежит некорректные данные - подумать самостоятельно.
 */


public class App {

    public static void main(String[] args) {

        // Создаём объект сериализуемого класса
        SomeSerializableClass someSerializableValue = new SomeSerializableClass("Some data");


        String dataUuid = null;

        // Записываем его в файл
        try {
            dataUuid = SerialaztionSaver.save(someSerializableValue);
        } catch (Exception e) {
            System.out.println(e.getCause());
        }



        String fileName = SomeSerializableClass.class.getName()+"_"+dataUuid;

        // Создаём объект ридера класса и объект, куда будем записывать;
        SerializationReader<SomeSerializableClass> serializationReader = new SerializationReader();
        SomeSerializableClass newValue = null;

        //Читаем
        try {
            newValue = serializationReader.read(fileName);
        } catch (Exception e) {
            System.out.println(e.getCause());
        }

        // Смотрим, что восстановили
        System.out.println(newValue.toString());





    }
}
