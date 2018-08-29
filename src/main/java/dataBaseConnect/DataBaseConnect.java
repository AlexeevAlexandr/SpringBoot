package dataBaseConnect;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Data //аннотация Lombok, добавляющая все рутинные методы get/set-методы, hashCode, equals и toString
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

    public DataBaseConnect(){
    }

    DataBaseConnect(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}

