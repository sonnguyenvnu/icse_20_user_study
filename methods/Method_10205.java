private static void testForEach(){
  ConcurrentHashMap<String,String> map=new ConcurrentHashMap<>();
  map.putIfAbsent("foo","bar");
  map.putIfAbsent("han","solo");
  map.putIfAbsent("r2","d2");
  map.putIfAbsent("c3","p0");
  map.forEach(1,(key,value) -> System.out.printf("key: %s; value: %s; thread: %s\n",key,value,Thread.currentThread().getName()));
  System.out.println(map.mappingCount());
}
