import java.sql.*;

public class MySqlDb {

    private static final String URL = "jdbc:mysql://188.242.140.127:3306";
    private static final String USER = "user";
    private static final String PASSWORD = "12345";

    public static void createAndWrite() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {

            Statement statement = connection.createStatement();

            statement.execute("CREATE SCHEMA IF NOT EXISTS books");
            statement.execute("USE books");
            statement.execute("CREATE TABLE IF NOT EXISTS book (" + "id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY," + "name VARCHAR(255)," + "author VARCHAR(255)" + ")");

            for (int i = 1; i < 11; i++) {
                statement.execute("INSERT INTO book (name, author) VALUES ('Книга " + i + "', 'Автор " + i + "')");
            }

            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void reedTable() {

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {

            Statement statement = connection.createStatement();

            ResultSet set = statement.executeQuery("SELECT * FROM books.book;");
            while (set.next()) {
                System.out.println(set.getString(1)+" "+ set.getString(2)+" "+set.getString(3));
            }

            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}