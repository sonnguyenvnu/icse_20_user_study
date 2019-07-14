private static void collections(){
  var list=List.of("A","B","C");
  var copy=List.copyOf(list);
  System.out.println(list == copy);
  var map=Map.of("A",1,"B",2);
  System.out.println(map);
}
