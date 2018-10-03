package dataBaseConnect;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "data_base_password")
public class DataBaseConnectPassword {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "id")
    private int id;
    @Column (name = "password")
    @Size(max = 50)
    private String password;


    public DataBaseConnectPassword(){
    }

    DataBaseConnectPassword(String password){
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

