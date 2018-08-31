package dataBaseConnect;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name = "data_base")
public class DataBaseConnect {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "id")
    private int id;
    @Column (name = "firstName")
    @Size(max = 50)
    private String firstName;
    @Column (name = "lastName")
    @Size(max = 50)
    private String lastName;
    @Email
    @Column (name = "email")
    @Size(max = 50)
    private String email;
    @Column(name = "date")
    private String date;

    public DataBaseConnect(){
    }

    DataBaseConnect(String firstName, String lastName, String email, String date){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

