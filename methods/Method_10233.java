private static void test3(){
  Outer outer=new Outer();
  resolve(() -> outer.getNested().getInner().getFoo()).ifPresent(System.out::println);
}
