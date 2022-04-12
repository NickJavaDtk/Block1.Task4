package Study.stream.task2;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>( );
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random( ).nextInt(names.size( ))),
                    families.get(new Random( ).nextInt(families.size( ))),
                    new Random( ).nextInt(100),
                    Sex.values( )[new Random( ).nextInt(Sex.values( ).length)],
                    Education.values( )[new Random( ).nextInt(Education.values( ).length)])
            );
        }
        long count = persons.stream( )
                .filter(age -> age.getAge( ) < 18)
                .count( );
        System.out.println("Количество несовершеннолетних " + count);
        List<String> listSurname = (List<String>) persons.stream( )
                .filter(age -> age.getAge( ) >= 18 && age.getAge( ) < 27)
                .filter(sex -> sex.getSex( ).equals(Sex.MAN))
                .map(family -> family.getFamily( ))
                .collect(Collectors.toList( ));

        List<Person> personList = persons.stream( )
                .filter(age -> age.getAge( ) >= 18 && age.getAge( ) < 65)
                .filter(education -> education.getEducation( ).equals(Education.HIGHER))
                .filter(person -> ((person.getSex( ).equals(Sex.WOMAN) && person.getAge( ) < 60)) || person.getSex( ).equals(Sex.MAN))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList( ));
    }
}
