private static List<Object> ordered(List<Object> list){
  List<Object> newList=new ArrayList<>(list.size());
  for (  Object obj : list) {
    newList.add(sortJson(obj));
  }
  return newList;
}
