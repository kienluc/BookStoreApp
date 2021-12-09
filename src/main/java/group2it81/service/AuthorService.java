package group2it81.service;

import java.util.Date;
import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;

import group2it81.configs.HibernateUtils;
import group2it81.pojo.Author;

public class AuthorService {
    public List<Author> getAuthor(){
        String hql = "FROM Author a";
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Query<Author> q = session.createQuery(hql);
            return q.list();
        }
    }
    public Boolean addAuthor(String name, String gender, Date birthDate){
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            try{ 
            session.getTransaction().begin();
            Author auth = new Author();
            auth.setHoTen(name);
            auth.setGioiTinh(gender);
            auth.setNgaySinh(birthDate);    

            session.save(auth);
            session.getTransaction().commit();
            }catch(Exception ex)
            {
                session.getTransaction().rollback();
                return false;
            }       
        }
        return true;           
    }
    public Boolean deleteAuthor(int id_author){
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            try{
                session.getTransaction().begin();
                Author auth = new Author();
                auth = (Author) session.get(Author.class, id_author);
                session.delete(auth);
                session.getTransaction().commit();
            }catch(Exception ex){
                session.getTransaction().rollback();
                return false;
            }
        }
        return true;
    } 
}
