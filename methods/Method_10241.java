private static void test7(List<Person> persons){
  Collector<Person,StringJoiner,String> personNameCollector=Collector.of(() -> new StringJoiner(" | "),(j,p) -> j.add(p.name.toUpperCase()),(j1,j2) -> j1.merge(j2),StringJoiner::toString);
  String names=persons.stream().collect(personNameCollector);
  System.out.println(names);
}
