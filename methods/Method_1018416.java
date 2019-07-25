@RequestMapping(value="set",produces=MediaType.APPLICATION_JSON_UTF8_VALUE) public void set(){
  Person person=new Person("1","wyf",32);
  personRepository.save(person);
  personRepository.stringRedisTemplateDemo();
}
