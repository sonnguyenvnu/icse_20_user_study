private static void test5(List<Person> persons){
  String names=persons.stream().filter(p -> p.age >= 18).map(p -> p.name).collect(Collectors.joining(" and ","In Germany "," are of legal age."));
  System.out.println(names);
}
