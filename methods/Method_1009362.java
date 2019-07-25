public static <T>List<T> from(Iterator<T> iterator){
  List<T> list=new ArrayList<T>();
  while (iterator.hasNext())   list.add(iterator.next());
  return list;
}
