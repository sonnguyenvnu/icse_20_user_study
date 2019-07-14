protected static Map<String,Collection> matchWithString(String pattern,Map<String,Collection> index){
  Pattern p=createPattern(pattern);
  Map<String,Collection> map=new HashMap<>();
  for (  String key : index.keySet()) {
    if (p.matcher(key).matches()) {
      map.put(key,index.get(key));
    }
  }
  return map;
}
