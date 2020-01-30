import domain.Person;
import storage.AbstractDAO;

import java.util.List;

public class App {

    public static void main(String[] args) {
        AbstractDAO abstractDAO = new AbstractDAO<Person>(Person.class);
        System.out.println("Connection was successful");
        String[] names = {"Andrey", "Dima", "Vasya"};
        String[] countries = {"Ukraine", "Russia", "USA"};
        long[] salaries = {100, 99, 150};
//        for (int i = 0; i<names.length; i++){
//            Person person = new Person();
//            person.setName(names[i]);
//            person.setCounty(countries[i]);
//            person.setSalary(salaries[i]);
//            abstractDAO.save(person);
//        }
        List<Person> persons =  abstractDAO.getAll();
        for (Person person : persons){
            System.out.println(person);
        }
    }
}
