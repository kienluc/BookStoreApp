package group2it81.pojo;

import java.io.Serializable;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = -6296266467719869628L;

    public User(){

    }
    
    @Id
    private int id;

    private String username;
    private String password;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private NhanVien nhanvien;

    public NhanVien getNhanVien() {
        return this.nhanvien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanvien = nhanVien;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String username, String pass, NhanVien nv){
        this.username = username;
        this.password = pass;
        this.nhanvien = nv;
    }
}