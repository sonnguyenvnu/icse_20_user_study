private static void test2(List<Person> persons){
  Map<Integer,List<Person>> personsByAge=persons.stream().collect(Collectors.groupingBy(p -> p.age));
  personsByAge.forEach((age,p) -> System.out.format("age %s: %s\n",age,p));
}
