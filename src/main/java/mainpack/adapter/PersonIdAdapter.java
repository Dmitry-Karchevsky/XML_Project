package mainpack.adapter;

import mainpack.Main;
import mainpack.entities.Person;
import mainpack.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.adapters.XmlAdapter;

@Component
public class PersonIdAdapter extends XmlAdapter<Long, Person> {

    @Autowired
    private PersonRepository personRepository;

    //the object PersonIdAdapter is created before spring creates its bean
    public Person unmarshal(Long id) {
        return Main.personRepository.findById(id).orElse(new Person()); //work only with Main.personRepository.(...)
    }

    public Long marshal(Person person) throws Exception {
        return person.getId();
    }
}
