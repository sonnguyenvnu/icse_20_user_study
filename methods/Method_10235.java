private static void test1(){
  Optional.of(new Outer()).flatMap(o -> Optional.ofNullable(o.nested)).flatMap(n -> Optional.ofNullable(n.inner)).flatMap(i -> Optional.ofNullable(i.foo)).ifPresent(System.out::println);
}
