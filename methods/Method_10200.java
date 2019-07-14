private static void streams(){
  System.out.println(Stream.ofNullable(null).count());
  System.out.println(Stream.of(1,2,3,2,1).dropWhile(n -> n < 3).collect(Collectors.toList()));
  System.out.println(Stream.of(1,2,3,2,1).takeWhile(n -> n < 3).collect(Collectors.toList()));
}
