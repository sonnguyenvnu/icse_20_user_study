private static Map<String,String> getenv(){
  Map<String,String> map=new HashMap<>();
  for (  Map.Entry<String,String> entry : System.getenv().entrySet()) {
    map.put(entry.getKey(),entry.getValue());
  }
  return map;
}
