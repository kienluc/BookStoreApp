package group2it81.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import group2it81.configs.HibernateUtils;
import group2it81.pojo.BookType;

public class BookTypeService {
    public List<BookType> getBookType(){
        String hql = "FROM BookType";
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Query<BookType> q = session.createQuery(hql);
            return q.list();
        }
    }
    public Boolean addCategory(String name, String description){
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            try{ 
            session.getTransaction().begin();
            BookType cat = new BookType();    
            cat.setTenLoai(name);
            cat.setMoTa(description);
            session.save(cat);
            session.getTransaction().commit();
            }catch(Exception ex)
            {
                session.getTransaction().rollback();
                return false;
            }       
        }
        return true;           
    }
    public Boolean deleteCategory(int id_cate){
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            try{
                session.getTransaction().begin();
                BookType cat = new BookType();
                cat = (BookType) session.get(BookType.class, id_cate);
                session.delete(cat);
                session.getTransaction().commit();
            }catch(Exception ex){
                session.getTransaction().rollback();
                return false;
            }
        }
        return true;
    }
}
