private static void testReduce(){
  ConcurrentHashMap<String,String> map=new ConcurrentHashMap<>();
  map.putIfAbsent("foo","bar");
  map.putIfAbsent("han","solo");
  map.putIfAbsent("r2","d2");
  map.putIfAbsent("c3","p0");
  String reduced=map.reduce(1,(key,value) -> key + "=" + value,(s1,s2) -> s1 + ", " + s2);
  System.out.println(reduced);
}
