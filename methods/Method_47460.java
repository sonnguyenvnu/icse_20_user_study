@RequestMapping(value="/save",method=RequestMethod.POST) public List<Person> savePerson(@RequestBody String personName){
  Person p=new Person(personName);
  personRepository.save(p);
  List<Person> people=personRepository.findAll(new PageRequest(0,10)).getContent();
  return people;
}
