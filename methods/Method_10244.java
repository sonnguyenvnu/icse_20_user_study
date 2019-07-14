private static void test1(List<Person> persons){
  persons.stream().reduce((p1,p2) -> p1.age > p2.age ? p1 : p2).ifPresent(System.out::println);
}
