private static void test3(){
  IntStream.range(0,10).average().ifPresent(System.out::println);
}
