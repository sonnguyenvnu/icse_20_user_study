/** 
 * Same as  {@link #convert(List)} but on an {@link Iterable}.
 */
private static List<?> convert(Iterable<?> values){
  List<?> list;
  if (values instanceof List<?>) {
    list=(List<?>)values;
  }
 else {
    ArrayList<Object> arrayList=new ArrayList<>();
    for (    Object o : values) {
      arrayList.add(o);
    }
    list=arrayList;
  }
  return convert(list);
}
