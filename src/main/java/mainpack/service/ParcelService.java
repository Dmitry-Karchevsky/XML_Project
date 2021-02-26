package mainpack.service;

import mainpack.adapter.PersonIdAdapter;
import mainpack.entities.Person;
import mainpack.repository.ParcelRepository;
import mainpack.entities.Parcel;
import mainpack.entities.ParcelList;
import mainpack.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;

@Service
public class ParcelService {

    @Autowired
    private ParcelRepository parcelRepository;

    @Autowired
    private PersonIdAdapter personIdAdapter;

    @Autowired
    private PersonRepository personRepository;

    private void validateXML(File xmlFile, File xsdFile) throws Exception {

        // Поиск и создание экземпляра фабрики для языка XML Schema
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        // Компиляция схемы
        // Схема загружается в объект типа java.io.File, но можно использовать
        // классы java.net.URL и javax.xml.transform.Source
        Schema schema = factory.newSchema(xsdFile);

        // Создание валидатора для схемы
        Validator validator = schema.newValidator();

        // Разбор проверяемого документа
        Source source = new StreamSource(xmlFile);

        // Валидация документа
        try {
            validator.validate(source);
            System.out.println(" is valid.");
        }
        catch (SAXException ex) {
            System.out.println(" is not valid because ");
            System.out.println(ex.getMessage());
            throw ex;
        }
    }

    public Parcel getParcelFromDbOnWeight(Integer weight) {
        return parcelRepository.findByWeight(weight);
    }

    public ParcelList getParcelListFromDbOnIdPersonFrom(Long idPersonFrom){
        Person personFrom = personRepository.findById(idPersonFrom).orElse(null);
        if (personFrom != null)
            return new ParcelList(parcelRepository.findAllByPersonFrom(personFrom));
        return null;
    }

    public Parcel getParcelFromXmlFile(File xmlFile){
        Parcel parcel;
        try {
            validateXML(xmlFile, new File("src\\main\\resources\\xsdFiles\\OneParcel.xsd"));

            JAXBContext jaxbContext = JAXBContext.newInstance(Parcel.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            parcel = (Parcel) jaxbUnmarshaller.unmarshal(xmlFile);
        }catch (JAXBException e) {
            System.out.println("JAXBException");
            return null;
        } catch (SAXException e) {
            System.out.println("SAXException");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return parcel;
    }

    public ParcelList getParcelListFromXmlFile(File xmlFile){
        ParcelList parcelList = null;
        try {
            validateXML(xmlFile, new File("src\\main\\resources\\xsdFiles\\Parcels.xsd"));

            JAXBContext jaxbContext = JAXBContext.newInstance(ParcelList.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            parcelList = (ParcelList) jaxbUnmarshaller.unmarshal(xmlFile);
        } catch (JAXBException e) {
            System.out.println("JAXBException");
            return null;
        } catch (SAXException e) {
            System.out.println("SAXException");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return parcelList;
    }

    public void convertParcelToXml(Parcel parcel, String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(Parcel.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // маршаллинг объекта в файл
            marshaller.marshal(parcel, new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void convertParcelListToXml(ParcelList parcelList, String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(ParcelList.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(parcelList, new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
