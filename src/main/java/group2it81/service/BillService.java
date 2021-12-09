package group2it81.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.Session;

import group2it81.configs.HibernateUtils;
import group2it81.pojo.Bill;
import group2it81.pojo.Customer;

public class BillService {
    public Boolean addBill(int id_customer, int total, Date date) throws ParseException{
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        date = new SimpleDateFormat("yyyy-MM-dd").parse(timeStamp);
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            try{ 
            session.getTransaction().begin();
            Bill bill = new Bill();
            Customer customer = session.get(Customer.class, id_customer);
            
            bill.setCustomer(customer);
            bill.setTongTien(total);
            bill.setNgayXuat(date);

            session.save(bill);
            session.getTransaction().commit();
            }catch(Exception ex)
            {
                session.getTransaction().rollback();
                return false;
            }       
        }
        return true;           
    }
    public Long countRevenue(int month, int year){
        String hql = String.format("SELECT SUM(B.tongTien) FROM Bill B WHERE month(B.ngayXuat) = %d AND year(B.ngayXuat) = %d",month,year);
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            return (Long) session.createQuery(hql).getSingleResult();  
        }
        
    }
}
