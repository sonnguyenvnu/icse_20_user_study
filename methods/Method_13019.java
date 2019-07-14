protected static Map<String,Collection> matchWithChar(char c,Map<String,Collection> index){
  if ((c == '*') || (c == '?')) {
    return index;
  }
 else {
    Map<String,Collection> map=new HashMap<>();
    for (    String key : index.keySet()) {
      if (!key.isEmpty() && (key.charAt(0) == c)) {
        map.put(key,index.get(key));
      }
    }
    return map;
  }
}
