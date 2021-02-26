package mainpack;

import mainpack.adapter.PersonIdAdapter;
import mainpack.entities.Country;
import mainpack.entities.Parcel;
import mainpack.entities.ParcelList;
import mainpack.repository.CountryRepository;
import mainpack.repository.ParcelRepository;
import mainpack.repository.PersonRepository;
import mainpack.service.ParcelService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.File;

@SpringBootApplication
public class Main {

    private static CountryRepository countryRepository;

    private static ParcelRepository parcelRepository;

    public static PersonRepository personRepository;

    private static ParcelService parcelService;

    private static ApplicationContext applicationContext;

    public Main(CountryRepository countryRepository,
                ParcelRepository parcelRepository,
                ParcelService parcelService,
                PersonRepository personRepository,
                ApplicationContext applicationContext) {
        this.countryRepository = countryRepository;
        this.parcelRepository = parcelRepository;
        this.parcelService = parcelService;
        this.personRepository = personRepository;
        this.applicationContext = applicationContext;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

        String fileName = "src\\main\\resources\\xmlFiles\\OneParcel.xml";
        File file = new File(fileName);

    }

    private static void checkBean(){
        String[] array = applicationContext.getBeanDefinitionNames();
        for (String s : array) {
            System.out.println(s);
        }
        PersonIdAdapter personIdAdapter = applicationContext.getBean(PersonIdAdapter.class);
        System.out.println(personIdAdapter);
    }

    private static void addCountry() {
        Country country = new Country();
        country.setId(1l);
        country.setName("Russia");
        countryRepository.save(country);
    }

    private static void convertListToXML(String fileName){
        ParcelList parcelList = parcelService.getParcelListFromDbOnIdPersonFrom(1l);
        System.out.println(parcelList.size());
        System.out.println(parcelList);
        parcelService.convertParcelListToXml(parcelList, fileName);
    }

    private static void getListFromXML(File file){
        ParcelList parcels = parcelService.getParcelListFromXmlFile(file);
        System.out.println(parcels);
    }

    private static void getParcelFromXML(File file){
        Parcel parcel2 = parcelService.getParcelFromXmlFile(file);
        System.out.println(parcel2);
        //parcelRepository.save(parcel2);
    }

    private static void getParcelFromDb(String fileName){
        Parcel parcel = parcelService.getParcelFromDbOnWeight(6172);
        System.out.println(parcel);
        parcelService.convertParcelToXml(parcel, fileName);
    }
}
