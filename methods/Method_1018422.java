@RequestMapping("/all") public List<Person> sort(){
  List<Person> people=personService.findAll();
  return people;
}
