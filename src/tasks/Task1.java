package tasks;

import common.Person;
import common.PersonService;
import common.Task;

import java.util.*;
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
    Set<Person> persons = PersonService.findPersons(personIds);
    Map<Integer,Person> mapPerson=new HashMap<>();
    for(Person person: persons){
      mapPerson.put(person.getId(),person);
    }
    List<Person> orderedPersons=new ArrayList<Person>();
    for(int id: personIds){
        orderedPersons.add(mapPerson.get(id));
    }
    return orderedPersons;//*/

      //Лайф хак за O(n)
    /*List<Person> orderedPersons=new ArrayList<Person>();
    for(int i=0;i<personIds.size();i++){
      List<Integer> personId=new ArrayList<>();
      personId.add(personIds.get(i));
        orderedPersons.addAll(PersonService.findPersons(personId));
    }
    return orderedPersons;//*/
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
