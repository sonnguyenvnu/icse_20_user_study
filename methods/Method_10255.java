private static void test1(){
  int[] ints={1,3,5,7,11};
  Arrays.stream(ints).average().ifPresent(System.out::println);
}
