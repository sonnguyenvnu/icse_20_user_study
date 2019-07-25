public static List project(List<Map> list,String key){
  List list1=new ArrayList(list.size());
  for (  Map map : list) {
    list1.add(map.get(key));
  }
  return list1;
}
