private static void test2(List<String> strings){
  strings.parallelStream().filter(s -> {
    System.out.format("filter:  %s [%s]\n",s,Thread.currentThread().getName());
    return true;
  }
).map(s -> {
    System.out.format("map:     %s [%s]\n",s,Thread.currentThread().getName());
    return s.toUpperCase();
  }
).forEach(s -> System.out.format("forEach: %s [%s]\n",s,Thread.currentThread().getName()));
}
