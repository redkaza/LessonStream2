import CountingPeople.Education;
import CountingPeople.Person;
import CountingPeople.Sex;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collection;
import java.util.stream.Stream;
import java.util.Comparator;

public class Main {

    public static void main(String args[]) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();

        for (int i = 0; i < 10000000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        // 1) Найти количество несовершеннолетних (т.е. людей младше 18 лет)
        Stream<Person> stream = persons.stream();
        long count = stream.filter(x -> x.getAge() < 18)
                //.forEach(System.out::println);
                .count();

        System.out.println(count);

        // 2) Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет)
        Stream<Person> stream2 = persons.stream();
        stream2.filter(x -> x.getAge() >= 18 & x.getAge() <= 27 & x.getSex() == Sex.MAN)
                //.distinct()
                //.forEach(System.out::println);
                .forEach(x -> System.out.println(x.getFamily()));

        // 3) Получить отсортированный по фамилии список потенциально работоспособных людей с высшим образованием в выборке
        // (т.е. людей с высшим образованием от 18 до 60 лет для женщин и до 65 лет для мужчин)
        Stream<Person> stream3 = persons.stream();
        stream3.filter(x -> x.getAge() >= 18 & x.getEducation() == Education.HIGHER & (x.getAge() <= 65 & x.getSex() == Sex.MAN | x.getAge() <= 60 & x.getSex() == Sex.WOMAN))
                .sorted(Comparator.naturalOrder())
                .forEach(System.out::println);
    }
}