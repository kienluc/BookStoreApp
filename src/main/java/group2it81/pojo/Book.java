package group2it81.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table (name = "sach")
public class Book implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String tenSach;
    private int lanTaiBan;
    private int donGia;
    private int soLuongTon;

    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "tacGia")
    private Author author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "nhaXB")
    private Producer producer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "loaiSach")
    private BookType loaisach;

    public BookType getLoaisach() {
        return this.loaisach;
    }

    public void setLoaisach(BookType loaisach) {
        this.loaisach = loaisach;
    }
    
    public Author getAuthor() {
        return this.author;
    }

    public void setAuthor(Author author2) {
        this.author = author2;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSach() {
        return this.tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getLanTaiBan() {
        return this.lanTaiBan;
    }

    public void setLanTaiBan(int lanTaiBan) {
        this.lanTaiBan = lanTaiBan;
    }

    public int getDonGia() {
        return this.donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getSoLuongTon() {
        return this.soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    

    public Producer getProducer() {
        return this.producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }


}
