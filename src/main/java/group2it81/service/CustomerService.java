package group2it81.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import group2it81.configs.HibernateUtils;
import group2it81.pojo.Customer;

public class CustomerService {
    public List<Customer> getCustomer(){
        String hql = "FROM Customer c";
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Query<Customer> q = session.createQuery(hql);
            return q.list();
        }
    }
    public Boolean addCustomer(String name, String phone){
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            try{ 
            session.getTransaction().begin();
            Customer customer = new Customer();
            customer.setHoTen(name);
            customer.setSdt(phone);

            session.save(customer);
            session.getTransaction().commit();
            }catch(Exception ex)
            {
                session.getTransaction().rollback();
                return false;
            }       
        }
        return true;           
    }
}
