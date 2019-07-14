private static void test4(List<Person> persons){
  Integer ageSum=persons.stream().reduce(0,(sum,p) -> {
    System.out.format("accumulator: sum=%s; person=%s\n",sum,p);
    return sum+=p.age;
  }
,(sum1,sum2) -> {
    System.out.format("combiner: sum1=%s; sum2=%s\n",sum1,sum2);
    return sum1 + sum2;
  }
);
  System.out.println(ageSum);
}
