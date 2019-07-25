protected List<T> transform(List<String> stringValues){
  List<T> list=new ArrayList<T>(stringValues.size());
  for (  String s : stringValues) {
    list.add(from(s));
  }
  return Collections.unmodifiableList(list);
}
