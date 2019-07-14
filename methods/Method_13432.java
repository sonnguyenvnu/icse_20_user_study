private static <T extends Collection<String>>void addAll(Map<String,T> source,MultiValueMap<String,String> destination){
  for (  Map.Entry<String,T> entry : source.entrySet()) {
    String key=entry.getKey();
    for (    String value : entry.getValue()) {
      add(key,value,destination);
    }
  }
}
