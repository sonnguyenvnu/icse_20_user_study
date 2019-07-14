private static void test3(List<Person> persons){
  Double averageAge=persons.stream().collect(Collectors.averagingInt(p -> p.age));
  System.out.println(averageAge);
}
