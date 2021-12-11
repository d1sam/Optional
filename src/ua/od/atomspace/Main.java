package ua.od.atomspace;

import java.util.Locale;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        Optional<Object> empty = Optional.empty();
        System.out.println(empty);// Optional.empty
        System.out.println(empty.isEmpty());// true
        System.out.println(empty.isPresent());// false
        Optional<String> hello = Optional.of("Hello");
        System.out.println(hello);// Optional[Hello]
        System.out.println(hello.isEmpty());// false
        System.out.println(hello.isPresent());// true
        String s1 = hello.orElse("smth");
        System.out.println(s1);
        // Optional<Object> objectOptional = Optional.of(null);
        // System.out.println(objectOptional.orElse("Hello")); // nullPointerException так как нужно было использовать ofNullable
        Optional<Object> objectOptional = Optional.ofNullable(null);
        System.out.println(objectOptional.orElse("OTHER"));

        Optional<String> helloUpper = Optional.ofNullable("Hello");
        String newStr = helloUpper.map(String::toUpperCase).orElseGet(() -> {// если опшинал пуст, можно реализовать свою логику, но все равно вернуть что-то
            System.out.println("Some logic");
            return "Nothing";
        });
        System.out.println(newStr);
        Optional<String> helloUpper2 = Optional.ofNullable("Hello");
        String newStr2 = helloUpper.map(String::toUpperCase).orElseThrow(IllegalAccessError::new);// если опшинал пуст, то можно выбросить эксепшн
        helloUpper.ifPresent(System.out::println);// если опшинал НЕ пуст, то распечатать его

        // если есть, то распечатай
        helloUpper.ifPresentOrElse(System.out::println, () -> System.out.println("world")); // если опшинал не пуст, то распечатать содержимое,
        // если нет, то своя логика
        // ----------------------------------------

        Person person = new Person("Сергей", "сергей@mail.ru");
        System.out.println(person.getEmail().toLowerCase());
        Person person2 = new Person("Сергей", null);
        System.out.println(person2.getEmail());// null pointer exception

        Person2 person3 = new Person2("Сергей", null);
        System.out.println(person3.getEmail().map(String::toLowerCase).orElse("У Сергея нет почты"));// У Сергея нет почты

        if (person3.getEmail().isPresent()){
            String res = person3.getEmail().get();
            System.out.println(res);
        }else{
            System.out.println("У Сергея нет почты");
        }
    }
}

class Person {
    private final String name;
    private final String email;

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

class Person2 {
    private final String name;
    private final String email;

    public Person2(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }
}
