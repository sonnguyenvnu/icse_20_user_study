public static Map<String,String> split(List<String> list,String separator){
  if (list == null) {
    return null;
  }
  Map<String,String> map=new HashMap<>();
  if (list.isEmpty()) {
    return map;
  }
  for (  String item : list) {
    int index=item.indexOf(separator);
    if (index == -1) {
      map.put(item,"");
    }
 else {
      map.put(item.substring(0,index),item.substring(index + 1));
    }
  }
  return map;
}
