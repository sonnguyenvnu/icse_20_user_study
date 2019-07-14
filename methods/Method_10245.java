private static void test3(List<Person> persons){
  Integer ageSum=persons.stream().reduce(0,(sum,p) -> sum+=p.age,(sum1,sum2) -> sum1 + sum2);
  System.out.println(ageSum);
}
