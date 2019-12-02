package tasks;

import common.Area;
import common.Person;
import common.Task;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/*
Имеются
- коллекция персон Collection<Person>
- словарь Map<Integer, Set<Integer>>, сопоставляющий каждой персоне множество id регионов
- коллекция всех регионов Collection<Area>
На выходе хочется получить множество строк вида "Имя - регион". Если у персон регионов несколько, таких строк так же будет несколько
 */
public class Task6 implements Task {

  private Set <String> getPersonDescriptions(Collection <Person> persons,
                                            Map <Integer, Set <Integer>> personAreaIds,
                                            Collection <Area> areas) {
  //V1
    Set <String> personDescription = new HashSet <>();
    Map <Integer,String> areaNames = new HashMap <>();
    areas.forEach(area -> areaNames.put(area.getId(),area.getName()));
    for(Person person:persons) {
      for (Integer areaId : personAreaIds.get(person.getId())) {
          personDescription.add(person.getFirstName() + " - " + areaNames.get(areaId));
      }
    }

    return personDescription;//*/
  }

  @Override
  public boolean check() {
    List <Person> persons = List.of(
        new Person(1, "Oleg", Instant.now()),
        new Person(2, "Vasya", Instant.now())
    );
    Map <Integer, Set<Integer>> personAreaIds = Map.of(1, Set.of(1, 2), 2, Set.of(2, 3));
    List<Area> areas = List.of(new Area(1, "Moscow"), new Area(2, "Spb"), new Area(3, "Ivanovo"));
    return getPersonDescriptions(persons, personAreaIds, areas)
        .equals(Set.of("Oleg - Moscow", "Oleg - Spb", "Vasya - Spb", "Vasya - Ivanovo"));
  }
}
