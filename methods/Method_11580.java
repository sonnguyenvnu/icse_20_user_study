@Override public List<String> selectList(String text){
  List<String> list=new ArrayList<String>();
  Object object=jsonPath.read(text);
  if (object == null) {
    return list;
  }
  if (object instanceof List) {
    List<Object> items=(List<Object>)object;
    for (    Object item : items) {
      list.add(toString(item));
    }
  }
 else {
    list.add(toString(object));
  }
  return list;
}
