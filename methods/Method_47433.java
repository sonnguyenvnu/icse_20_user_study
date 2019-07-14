public Person findOne(Long id){
  Person person=getCache(id,cacheManager);
  if (person != null) {
    System.out.println("??????:" + person.toString());
  }
 else {
    person=personRepository.findOne(id);
    System.out.println("???????:" + person.toString());
  }
  return person;
}
