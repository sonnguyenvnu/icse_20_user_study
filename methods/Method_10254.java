private static void test2(){
  IntStream.builder().add(1).add(3).add(5).add(7).add(11).build().average().ifPresent(System.out::println);
}
