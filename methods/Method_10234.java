private static void test2(){
  Optional.of(new Outer()).map(Outer::getNested).map(Nested::getInner).map(Inner::getFoo).ifPresent(System.out::println);
}
