package mainpack.entities;

import mainpack.adapter.LocalDateTimeAdapter;
import mainpack.adapter.PersonIdAdapter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@Entity
@Table(name = "parcel")
public class Parcel {
    @Id
    @GenericGenerator(name="parcels_seq", strategy = "com.jayash.domain.UseExistingOrGenerateIdGenerator")
    //@GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "parcels_seq")
    @SequenceGenerator(name="parcels_seq", sequenceName="parcel_id_seq", allocationSize=1)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_person_from")
    private Person personFrom;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_person_to")
    private Person personTo;

    @Column(name = "delivery_time", nullable = false)
    private int deliveryTime;

    @Column(name = "weight", nullable = false)
    private int weight;

    @Column(name = "dep_date", nullable = false)
    private LocalDateTime depDate;

    public Parcel() {
    }

    public Parcel(Long id, Person personFrom, Person personTo, int deliveryTime, int weight, LocalDateTime depDate) {
        this.id = id;
        this.personFrom = personFrom;
        this.personTo = personTo;
        this.deliveryTime = deliveryTime;
        this.weight = weight;
        this.depDate = depDate;
    }

    public Long getId() {
        return id;
    }

    @XmlAttribute
    public void setId(Long id) {
        this.id = id;
    }

    public Person getPersonFrom() {
        return personFrom;
    }

    @XmlElement(name = "id_person_from")
    @XmlJavaTypeAdapter(value = PersonIdAdapter.class)
    public void setPersonFrom(Person personFrom) {
        this.personFrom = personFrom;
    }

    public Person getPersonTo() {
        return personTo;
    }

    @XmlElement(name = "id_person_to")
    @XmlJavaTypeAdapter(value = PersonIdAdapter.class)//как сделать без class
    public void setPersonTo(Person personTo) {
        this.personTo = personTo;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    @XmlElement(name = "delivery_time")
    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public int getWeight() {
        return weight;
    }

    @XmlElement
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public LocalDateTime getDepDate() {
        return depDate;
    }

    @XmlElement(name = "dep_date")
    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    public void setDepDate(LocalDateTime depDate) {
        this.depDate = depDate;
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "id=" + id +
                ", id_person_from=" + personFrom +
                ", id_person_to=" + personTo +
                ", delivery_time=" + deliveryTime +
                ", weight=" + weight +
                ", dep_date=" + depDate +
                '}';
    }
}
