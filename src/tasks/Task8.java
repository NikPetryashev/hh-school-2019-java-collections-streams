package tasks;

import common.Person;
import common.Task;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 implements Task {

  //private long count; //неиспользуемая переменная

  //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
  public List<String> getNames(List<Person> persons) {
    return persons.stream()
            .skip(1)                  //пропускаем первую персону, если передать пустой список или с 1 персоной- вернется пустой список
            .map(Person::getFirstName)
            .collect(Collectors.toList());
  }

  //ну и различные имена тоже хочется
  public Set<String> getDifferentNames(List<Person> persons) {
    //В Stream лишняя операция distinct, так как коллекция Set содержит только уникальные значения
    //Но и стрим здесь не нужен
    return new HashSet<>(getNames(persons));
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  public String convertPersonToString(Person person) {
    //Добавил вывод отчества вместо повторного вывода фамилии
   //код выглядит чище и компактней, если завернуть все в стрим
    //нет лишнего пробела при сборке полного имени, если один из параметров оказался null
    return Stream.of(person.getSecondName(),person.getFirstName(),person.getMiddleName())
            .filter(name->name!=null)
            .collect(Collectors.joining(" "));
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    //сделаем все через стрим, при дубликатах оставляем старое значение (так в исходном коде)
    return persons.stream()
            .collect(Collectors.toMap(Person::getId,person -> convertPersonToString(person),(oldPerson, newPerson) -> oldPerson));
  }

  // есть ли совпадающие в двух коллекциях персоны?
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    return !Collections.disjoint(persons1,persons2); //для такого сравнения воспользуемся методом коллекции
                                                      // оставил отрицание, чтобы меотд возвращал тоже булевое значение,что и до рефакторинга
                                                    //но возможно стоит оставить true (когда в колекциях все элементы различны) и пересмотреть методы, где используется эта функция
  }

  //Выглядит вроде неплохо...
  public long countEven(Stream<Integer> numbers) {
    //для подсчета количества четных чисел в стриме воспользуемся готовым методом  count
    return numbers.filter(num->num % 2 == 0).count();
  }

  @Override
  public boolean check() {
    //System.out.println("Слабо дойти до сюда и исправить Fail этой таски?");
    boolean codeSmellsGood = true;
    boolean backendDeveloperVeryGood = true;
    return codeSmellsGood && backendDeveloperVeryGood;
  }
}
