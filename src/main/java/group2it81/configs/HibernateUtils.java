package group2it81.configs;


import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import group2it81.pojo.Author;
import group2it81.pojo.Bill;
import group2it81.pojo.BillDetail;
import group2it81.pojo.Book;
import group2it81.pojo.BookType;
import group2it81.pojo.Customer;
import group2it81.pojo.Producer;
import group2it81.pojo.Role;
import group2it81.pojo.User;
import group2it81.pojo.NhanVien;

public class HibernateUtils {
    private static final SessionFactory FACTORY;
    
    static {
        Configuration conf = new Configuration();
        
        
        Properties props = new Properties();
        props.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        props.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        props.put(Environment.URL, "jdbc:mysql://localhost:3306/bookstoredb");
        props.put(Environment.USER, "root");
        props.put(Environment.PASS, "12345678");
        props.put(Environment.SHOW_SQL, "true");

        conf.addAnnotatedClass(User.class);
        conf.addAnnotatedClass(Role.class);
        conf.addAnnotatedClass(NhanVien.class);
        conf.addAnnotatedClass(Author.class);
        conf.addAnnotatedClass(Bill.class);
        conf.addAnnotatedClass(BillDetail.class);
        conf.addAnnotatedClass(Book.class);
        conf.addAnnotatedClass(BookType.class);
        conf.addAnnotatedClass(Customer.class);
        conf.addAnnotatedClass(Producer.class);
        
        conf.setProperties(props);
        
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        
        FACTORY = conf.buildSessionFactory(registry);
    }

    public static final SessionFactory getSessionFactory(){
        return FACTORY;
    }
}
