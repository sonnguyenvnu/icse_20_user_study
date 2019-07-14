private static void testSearch(){
  ConcurrentHashMap<String,String> map=new ConcurrentHashMap<>();
  map.putIfAbsent("foo","bar");
  map.putIfAbsent("han","solo");
  map.putIfAbsent("r2","d2");
  map.putIfAbsent("c3","p0");
  System.out.println("\nsearch()\n");
  String result1=map.search(1,(key,value) -> {
    System.out.println(Thread.currentThread().getName());
    if (key.equals("foo") && value.equals("bar")) {
      return "foobar";
    }
    return null;
  }
);
  System.out.println(result1);
  System.out.println("\nsearchValues()\n");
  String result2=map.searchValues(1,value -> {
    System.out.println(Thread.currentThread().getName());
    if (value.length() > 3) {
      return value;
    }
    return null;
  }
);
  System.out.println(result2);
}
