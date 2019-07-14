public static Object[] toArray(ArrayNode v){
  if (v == null) {
    return null;
  }
  Object[] result=new Object[v.size()];
  for (int i=0; i != v.size(); i++) {
    result[i]=JsonValueConverter.convert(v.get(i));
  }
  return result;
}
