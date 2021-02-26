package mainpack.entities;

import java.time.LocalDate;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "sex", nullable = false)
    private String sex;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city")
    private City idCity;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    public Person() {
    }

    public Person(Long id) {
        this.id = id;
    }

    public Person(Long id, String name, String surname, String status, String sex, City idCity, String address, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.status = status;
        this.sex = sex;
        this.idCity = idCity;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    @XmlAttribute
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    @XmlElement
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getStatus() {
        return status;
    }

    @XmlElement
    public void setStatus(String status) {
        this.status = status;
    }

    public String getSex() {
        return sex;
    }

    @XmlElement
    public void setSex(String sex) {
        this.sex = sex;
    }

    public City getIdCity() {
        return idCity;
    }

    @XmlElement
    public void setIdCity(City idCity) {
        this.idCity = idCity;
    }

    public String getAddress() {
        return address;
    }

    @XmlElement
    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @XmlElement
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", status='" + status + '\'' +
                ", sex='" + sex + '\'' +
                ", idCity=" + idCity +
                ", address='" + address + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
