import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;

public class MySqlDbHyb {



    public static void write() {

       try {
           final StandardServiceRegistry REGESTRY = new StandardServiceRegistryBuilder().configure().build();


        SessionFactory sessionFactory = new MetadataSources(REGESTRY).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();

        Book book = new Book(100,"Книга100","Автор100");

        session.beginTransaction();
        for (int i = 11; i < 21; i++) {
            session.save(new Book(null, "Книга "+i,"Автор "+i%3));
        }

        session.getTransaction().commit();
        session.close();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }


    }

    public static List<Book> reed(String author) {

        final StandardServiceRegistry REGESTRY = new StandardServiceRegistryBuilder().configure().build();

        SessionFactory sessionFactory = new MetadataSources(REGESTRY).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();
        List<Book> books = null;

        try {
            Transaction transaction = session.beginTransaction();

            Query<Book> query = session.createQuery("FROM Book WHERE author = :author", Book.class);
            query.setParameter("author", author);
            books = query.getResultList();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return books;
    }

}
