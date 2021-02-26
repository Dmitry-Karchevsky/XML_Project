package mainpack.service;

import mainpack.entities.City;
import mainpack.entities.Parcel;
import mainpack.entities.Person;
import mainpack.repository.ParcelRepository;
import mainpack.repository.PersonRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
class ParcelServiceTest {

    @Autowired
    private ParcelService parcelService;

    @MockBean
    private ParcelRepository parcelRepository;

    @MockBean
    private PersonRepository personRepository;

    private final String fileNameOneParcel = "src\\main\\resources\\xmlFiles\\OneParcel.xml";
    private final String fileNameParcelList = "src\\main\\resources\\xmlFiles\\Parcels.xml";
    private final File xmlFileOneParcel = new File(fileNameOneParcel);
    private final File xmlFileParcelList = new File(fileNameParcelList);

    @Test
    void getParcelFromDbOnWeight() {
        Mockito.doReturn(new Parcel(1L, new Person(), new Person(), 333, 6172, LocalDateTime.parse("2016-09-18T13:34:44")))
                .when(parcelRepository)
                .findByWeight(6172);

        Parcel parcel = parcelService.getParcelFromDbOnWeight(6172);
        System.out.println(parcel);
        Assert.assertNotNull(parcel);
    }

    @Test
    void getParcelFromXmlFile() {
        Person person100 = new Person(100L, "Ivan", "Ivanov", "Single",
                "M", new City(), "Metallurg ST", LocalDate.parse("2016-09-18"));
        Optional<Person> optionalPerson100 = Optional.of(person100);
        Mockito.doReturn(optionalPerson100)
                .when(personRepository)
                .findById(100L);

        Person person200 = new Person(200L, "Petr", "Petrov", "Single",
                "M", new City(), "Metallurg ST", LocalDate.parse("2010-09-18"));
        Optional<Person> optionalPerson200 = Optional.of(person200);
        Mockito.doReturn(optionalPerson200)
                .when(personRepository)
                .findById(200L);

        Parcel parcel = parcelService.getParcelFromXmlFile(xmlFileOneParcel);
        System.out.println(parcel);
        Assert.assertNotNull(parcel);
    }

    @Test
    void getParcelListFromXmlFile() {
        
    }

    @Test
    void convertParcelToXml() {
        Parcel parcel = new Parcel(1L, new Person(1L), new Person(2L), 333, 6172, LocalDateTime.parse("2016-09-18T13:34:44"));
        parcelService.convertParcelToXml(parcel, "src\\main\\resources\\xmlFiles\\Parcel_2_test.xml");
    }

    @Test
    void convertParcelListToXml() {
    }
}