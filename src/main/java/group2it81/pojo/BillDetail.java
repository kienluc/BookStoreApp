package group2it81.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "chitiethoadon")
public class BillDetail implements Serializable {
    private int donGia;
    @Id
    @Column (name = "hoadon_id")
    private int idHoaDon;

    public int getIdHoaDon() {
        return this.idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    @Id
    @Column (name = "sach_id")
    private int idSach;

    public int getIdSach() {
        return this.idSach;
    }

    public void setIdSach(int idSach) {
        this.idSach = idSach;
    }

    @ManyToOne
    @JoinColumn (name = "hoadon_id")
    private Bill bill;

   @ManyToOne
   @JoinColumn (name = "sach_id")
   private Book book;

   public Book getBook() {
       return this.book;
   }

   public void setBook(Book book) {
       this.book = book;
   }
    
   public int getDonGia() {
    return this.donGia;
}

public void setDonGia(int donGia) {
    this.donGia = donGia;

}
public Bill getBill() {
    return this.bill;
}

public void setBill(Bill bill) {
    this.bill = bill;
}
}
