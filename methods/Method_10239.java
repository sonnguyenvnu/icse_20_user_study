private static void test4(List<Person> persons){
  IntSummaryStatistics ageSummary=persons.stream().collect(Collectors.summarizingInt(p -> p.age));
  System.out.println(ageSummary);
}
