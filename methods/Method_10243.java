private static void test9(List<Person> persons){
  Collector<Person,StringJoiner,String> personNameCollector=Collector.of(() -> {
    System.out.println("supplier");
    return new StringJoiner(" | ");
  }
,(j,p) -> {
    System.out.format("accumulator: p=%s; j=%s\n",p,j);
    j.add(p.name.toUpperCase());
  }
,(j1,j2) -> {
    System.out.println("merge");
    return j1.merge(j2);
  }
,j -> {
    System.out.println("finisher");
    return j.toString();
  }
);
  String names=persons.parallelStream().collect(personNameCollector);
  System.out.println(names);
}
