@Override public String select(String text){
  Object object=jsonPath.read(text);
  if (object == null) {
    return null;
  }
  if (object instanceof List) {
    List list=(List)object;
    if (list != null && list.size() > 0) {
      return toString(list.iterator().next());
    }
  }
  return object.toString();
}
