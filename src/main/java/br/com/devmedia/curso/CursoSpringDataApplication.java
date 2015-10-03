package br.com.devmedia.curso;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import br.com.devmedia.curso.entity.Address;
import br.com.devmedia.curso.entity.Address.TypeAddress;
import br.com.devmedia.curso.entity.Document;
import br.com.devmedia.curso.entity.Person;
import br.com.devmedia.curso.entity.Phone;
import br.com.devmedia.curso.entity.Phone.TypePhone;
import br.com.devmedia.curso.repository.AddressRepository;
import br.com.devmedia.curso.repository.DocumentRepository;
import br.com.devmedia.curso.repository.PersonRepository;
import br.com.devmedia.curso.repository.PhoneRepository;

@SpringBootApplication
//@ImportResource(value = "spring-data.xml")
public class CursoSpringDataApplication implements CommandLineRunner {
	
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private DocumentRepository documentRepository;
	@Autowired
	private PhoneRepository phoneRepository; 
	
    public static void main(String[] args) {
        SpringApplication.run(CursoSpringDataApplication.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		
		//testConfiguration();
		//testSave();
		//testUpdate();
		//testDelete();
		//testSavePersons();
		//testDeletePersons();
		//testFindAndSort();
		//testFindByIds();
		//testExists();
		testPagination();
	
	}

	private void testPagination() {
		
		Page<Person> pages = personRepository.findAll(new PageRequest(0, 3));
		pages.forEach(System.out::println);
		
		pages = personRepository.findAll(new PageRequest(1, 3));
		pages.forEach(System.out::println);
		
		pages = personRepository.findAll(new PageRequest(2, 3));
		pages.forEach(System.out::println);
		
		pages = personRepository.findAll(new PageRequest(3, 3));
		pages.forEach(System.out::println);
		
		pages = personRepository.findAll(new PageRequest(3, 3));
		pages.forEach(System.out::println);
		
		
		
	}

	private void testExists() {
		boolean P1 = personRepository.exists(1L);
		boolean P2 = personRepository.exists(4L);
		
		System.out.println("P1 is "+P1);
		System.out.println("P2 is "+P2);
		
	}

	private void testFindByIds() {
		
		List<Person> lista = personRepository.findAll(Arrays.asList(2L,4L,10L,16L,21L));
		lista.forEach(System.out::println);
		
	}

	private void testFindAndSort() {
		
		Order orderAsc = new Order(Direction.ASC,"lastName");	
		Order orderDesc = new Order(Direction.ASC,"firstName");	
	
		Sort sort = new Sort(orderAsc,orderDesc);
		
		List<Person> lista = personRepository.findAll(sort);
		
		lista.forEach(System.out::println);
		
	}

	private void testDeletePersons() {
		
		Person p1 = personRepository.findOne(25L);
		Person p2 = personRepository.findOne(26L);
		Person p3 = personRepository.findOne(27L);
		
		personRepository.delete(Arrays.asList(p1, p2, p3));
		
		System.out.println("********************************************");
		
		Person p4 = personRepository.findOne(28L);
		Person p5 = personRepository.findOne(29L);
		Person p6 = personRepository.findOne(30L);
		
		personRepository.deleteInBatch(Arrays.asList(p4, p5, p6));
		
		
	}

	private void testSavePersons() {
		
		Person p1 = new Person();
		p1.setFirstName("Alisson");
		p1.setLastName("Souza");
		p1.setAge(25);
		p1.setDocument(new Document("741.321.365-96", 963258852));
		
		Person p2 = new Person();
		p2.setFirstName("Bruno");
		p2.setLastName("Pereira");
		p2.setAge(63);
		p2.setDocument(new Document("741.369.212-96", 123654741));
		
		Person p3 = new Person();
		p3.setFirstName("Carlos Ricardo");
		p3.setLastName("Pereira");
		p3.setAge(45);
		p3.setDocument(new Document("701.309.212-96", 123054701));
		
		Person p4 = new Person();
		p4.setFirstName("Fabio");
		p4.setLastName("Guimarães");
		p4.setAge(33);
		p4.setDocument(new Document("742.329.212-96", 123624721));
		
		Person p5 = new Person();
		p5.setFirstName("Vinicius");
		p5.setLastName("Pereira");
		p5.setAge(23);
		p5.setDocument(new Document("141.319.212-96", 113614741));
		
		Person p6 = new Person();
		p6.setFirstName("Carla");
		p6.setLastName("Ribas");
		p6.setAge(23);
		p6.setDocument(new Document("541.315.212-96", 113655745));
		
		List<Person> persons = 		
				personRepository.save(Arrays.asList(p1, p2, p3, p4, p5 ,p6));
		
		persons.forEach(System.out::println);
		
	}

	private void testDelete() {
		
		personRepository.delete(13L);
		
		Person person = personRepository.findOne(13L);
		
		personRepository.delete(person);
		
		List<Person> persons = personRepository.findAll();
		persons.forEach(System.out::println);
		
	}

	private void testUpdate() {
		
		Person person = personRepository.findOne(15L);
		
		System.out.println(person.toString());
		
		person.setLastName("Aguiar");
		
		personRepository.save(person);
		
		Person p2 =  personRepository.findOne(15L);
		
		System.out.println(p2.toString());
		
	}

	private void testSave() {
		
		Person person = new Person();
		person.setFirstName("João Luiz");
		person.setLastName("Rios");
		person.setAge(35);
		person.setDocument(new Document("841.852.963-74", 12365485));
		
		Address address = new Address();
		address.setCity("Manaus");
		address.setStreet("Rua das Valquirias, 32");
		address.setType(TypeAddress.RESIDENCIAL);
		
		person.setAddresses(Arrays.asList(address));
		person.addPhone(new Phone(TypePhone.RESIDENCIAL, "32220303"));
		
		personRepository.save(person);
		
		Person p2 = personRepository.findOne(person.getId());
		
		System.out.println(p2.toString());
		
	}

	private void testConfiguration() {
		
		long total = personRepository.count();		
		System.out.println("Total of persons = " + total);
		
		List<Person> persons = personRepository.findAll();
		persons.forEach(System.out::println);
		
		long t2 = addressRepository.count();		
		System.out.println("Total of addresses = " + t2);
		
		long t3 = documentRepository.count();		
		System.out.println("Total of documents = " + t3);
		
		long t4 = phoneRepository.count();		
		System.out.println("Total of phones = " + t4);
		
	}
}
