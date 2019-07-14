private static void test1(List<Person> persons){
  List<Person> filtered=persons.stream().filter(p -> p.name.startsWith("P")).collect(Collectors.toList());
  System.out.println(filtered);
}
