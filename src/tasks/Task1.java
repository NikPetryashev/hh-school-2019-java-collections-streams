package tasks;

import common.Person;
import common.PersonService;
import common.Task;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
Задача 1
Метод на входе принимает List<Integer> id людей, ходит за ними в сервис (он выдает несортированный Set<Person>)
нужно их отсортировать в том же порядке, что и переданные id.
Оценить асимпотику работы
 */
public class Task1 implements Task {

  // !!! Редактируйте этот метод !!!
  private List<Person> findOrderedPersons(List<Integer> personIds) {
    //Решение за O(2n)
    Map<Integer,Person> mapPerson=PersonService.findPersons(personIds).stream()
              .collect(Collectors.toMap(Person::getId, Function.identity()));

    return personIds.stream()
            .map(personId->mapPerson.get(personId))
            .collect(Collectors.toList());//*/
  }

  @Override
  public boolean check() {
    List<Integer> ids = List.of(2, 1, 3);

    return findOrderedPersons(ids).stream()
        .map(Person::getId)
        .collect(Collectors.toList())
        .equals(ids);
  }

}
