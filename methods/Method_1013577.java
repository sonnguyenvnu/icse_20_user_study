default List<Value> all(){
  final List<Value> values=new ArrayList<Value>();
  Value elem;
  while ((elem=nextElement()) != null) {
    values.add(elem);
  }
  return values;
}
