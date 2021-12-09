package group2it81.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import group2it81.configs.HibernateUtils;
import group2it81.pojo.Producer;

public class ProducerService {
    public List<Producer> getProducer(){
        String hql = "FROM Producer";
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Query<Producer> q = session.createQuery(hql);
            return q.list();
        }
    }
    public Boolean addPublisher(String name, String address, String contact){
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            try{ 
            session.getTransaction().begin();
            Producer prod = new Producer();
            prod.setTenNXB(name);
            prod.setDiaChi(address);
            prod.setLienHe(contact);

            session.save(prod);
            session.getTransaction().commit();
            }catch(Exception ex)
            {
                session.getTransaction().rollback();
                return false;
            }       
        }
        return true;           
    }
    public Boolean deletePublisher(int id_publisher){
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            try{
                session.getTransaction().begin();
                Producer prod = new Producer();
                prod = (Producer) session.get(Producer.class, id_publisher);
                session.delete(prod);
                session.getTransaction().commit();
            }catch(Exception ex){
                session.getTransaction().rollback();
                return false;
            }
        }
        return true;
    }
}   
