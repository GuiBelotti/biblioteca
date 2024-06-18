package src.infrastructure;

import features.book.model.Book;
import src.features.loans.model.Loan;
import features.user.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DatabaseManager {

    private static SessionFactory sessionFactory;

    public static SessionFactory getDatabaseSessionFactory() {
        if(sessionFactory == null) {
            createSessionFactory();
        }
        return sessionFactory;
    }

    private static void createSessionFactory() {
        final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .build();
        try {
            sessionFactory = new MetadataSources(registry)
                    .addAnnotatedClass(Book.class)
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Loan.class)
                    .buildMetadata()
                    .buildSessionFactory();

        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}