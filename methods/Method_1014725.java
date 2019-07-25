public static List<String> join(Map<String,String> map,String separator){
  if (map == null) {
    return null;
  }
  List<String> list=new ArrayList<>();
  if (map.size() == 0) {
    return list;
  }
  for (  Map.Entry<String,String> entry : map.entrySet()) {
    String key=entry.getKey();
    String value=entry.getValue();
    if (StringUtils.isEmpty(value)) {
      list.add(key);
    }
 else {
      list.add(key + separator + value);
    }
  }
  return list;
}
