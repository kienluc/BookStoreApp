package group2it81.service;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

import group2it81.configs.HibernateUtils;
import group2it81.pojo.Author;
import group2it81.pojo.Book;
import group2it81.pojo.BookType;
import group2it81.pojo.Producer;

public class BookService {
    public List<Book> loadBooks(){
        String hql = "FROM Book";
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            Query<Book> q = session.createQuery(hql);
            return q.list();
        }
        
    }
    
    public List<Book> searchBookByKeyWord(String kw){
        String hqlBook = String.format("FROM Book book WHERE book.tenSach like '%%%s%%'", kw);
        String hqlCat = String.format("FROM BookType type WHERE type.tenLoai like '%%%s%%'", kw);
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            List<BookType> listBookFromCat = session.createQuery(hqlCat).list();
            List<Book> listBook = session.createQuery(hqlBook).list();
            
            listBookFromCat.forEach(typeBook ->{
                typeBook.getBooks().forEach(book ->{
                    if(!listBook.contains(book))
                        listBook.add(book);
                });
            });
            return listBook;
        }
    }

    public List<Book> searchBookByName(String kw){
        String hql = String.format("FROM Book book WHERE book.tenSach like '%%%s%%'", kw);
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Query<Book> query = session.createQuery(hql);
            return query.list();
        }
    }

    public List<Book> searchBookById(int string){
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            String hql = String.format("FROM Book b WHERE b.id = %d", string);
            Query<Book> q = session.createQuery(hql);
            return q.list();
        }
    }

    public Boolean addBook(Book sach, int id_author, int id_bt, int id_nxb){
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            try{ 
            session.getTransaction().begin();
            Author author = session.get(Author.class, id_author);
            BookType cate = session.get(BookType.class, id_bt);
            Producer producer = session.get(Producer.class, id_nxb);
            
            sach.setAuthor(author);
            sach.setProducer(producer);
            sach.setLoaisach(cate);

            session.save(sach);
            session.getTransaction().commit();
            }catch(Exception ex)
            {
                session.getTransaction().rollback();
                return false;
            }       
        }
        return true;           
    }  
    public Boolean deleteBook(int id_book){
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            try{
                session.getTransaction().begin();
                Book b = new Book();
                b = (Book) session.get(Book.class, id_book);
                session.delete(b);
                session.getTransaction().commit();
            }catch(Exception ex){
                session.getTransaction().rollback();
                return false;
            }
        }
        return true;
    }          
}
