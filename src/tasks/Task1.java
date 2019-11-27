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
    //Set<Person> persons = PersonService.findPersons(personIds);
    // V1 no Stream O((n^2))
    /*List<Person> persons=new ArrayList<Person>(PersonService.findPersons(personIds));
    List<Person> personsSort=new ArrayList<Person>();
    for(Integer personId:personIds){
        for(Person person:persons){
            if(person.getId()==personId)
                personsSort.add(person);
        }
    }
     return persons;//*/

    //V1 no Stream O((n^2)*log(n))
    /*List<Person> persons=new ArrayList<Person>(PersonService.findPersons(personIds));
    persons.sort(Comparator.comparing(p->personIds.indexOf(p.getId())));
    return persons; //*/

    //V2 stream  O((n^2)*log(n))
    return  PersonService.findPersons(personIds).stream()
            .sorted(Comparator.comparing(p->personIds.indexOf(p.getId())))
            .collect(Collectors.toList());//*/

    //Лайф хак за O(n)
   /* List<Person> persons=new ArrayList<Person>();
    for(int i=0;i<personIds.size();i++){
      List<Integer> personIds1=new ArrayList<>();
      personIds1.add(personIds.get(i));
      persons.addAll(PersonService.findPersons(personIds1));
    }
    return persons;//*/

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
