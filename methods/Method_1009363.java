public static <T>List<T> from(Set<Map.Entry<T,?>> entries){
  List<T> list=new ArrayList<T>();
  for (  Map.Entry<T,?> entry : entries)   list.add(entry.getKey());
  return list;
}
