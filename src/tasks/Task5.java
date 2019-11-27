package tasks;

import common.ApiPersonDto;
import common.Person;
import common.Task;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
Задача 5
Расширим предыдущую задачу
Есть список персон, и словарь сопоставляющий id каждой персоны и id региона
Необходимо выдать список персон ApiPersonDto, с правильно проставленными areaId
Конвертер одной персоны дополнен!
 */
public class Task5 implements Task {

  // !!! Редактируйте этот метод !!!
  private List<ApiPersonDto> convert(List<Person> persons, Map<Integer, Integer> personAreaIds) {
    //V1 no stream
    /*List <ApiPersonDto> convertPersons= new ArrayList<ApiPersonDto>();
    for(Person p:persons){
      convertPersons.add(convert(p,personAreaIds.get(p.getId())));
    }
    return convertPersons;//*/
    //V2 stream
    return persons.stream()
           .map((p)->convert(p,personAreaIds.get(p.getId())))
           .collect(Collectors.toList());//*/

    //return new ArrayList<>();
  }

  private static ApiPersonDto convert(Person person, Integer areaId) {
    ApiPersonDto dto = new ApiPersonDto();
    dto.setCreated(person.getCreatedAt().toEpochMilli());
    dto.setId(person.getId().toString());
    dto.setName(person.getFirstName());
    dto.setAreaId(areaId);
    return dto;
  }

  @Override
  public boolean check() {
    Person person1 = new Person(1, "Name", Instant.now());
    Person person2 = new Person(2, "Name", Instant.now());
    Map<Integer, Integer> personAreaIds = Map.of(1, 1, 2, 2);
    return List.of(convert(person1, 1), convert(person2, 2))
        .equals(convert(List.of(person1, person2), personAreaIds));
  }
}
