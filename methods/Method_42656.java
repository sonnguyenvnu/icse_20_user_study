/** 
 * @param value
 * @return
 */
private static List<String> makeStringList(Object value){
  if (value == null) {
    value="";
  }
  List<String> result=new ArrayList<String>();
  if (value.getClass().isArray()) {
    for (int j=0; j < Array.getLength(value); j++) {
      Object obj=Array.get(value,j);
      result.add(obj != null ? obj.toString() : "");
    }
    return result;
  }
  if (value instanceof Iterator) {
    Iterator it=(Iterator)value;
    while (it.hasNext()) {
      Object obj=it.next();
      result.add(obj != null ? obj.toString() : "");
    }
    return result;
  }
  if (value instanceof Collection) {
    for (    Object obj : (Collection)value) {
      result.add(obj != null ? obj.toString() : "");
    }
    return result;
  }
  if (value instanceof Enumeration) {
    Enumeration enumeration=(Enumeration)value;
    while (enumeration.hasMoreElements()) {
      Object obj=enumeration.nextElement();
      result.add(obj != null ? obj.toString() : "");
    }
    return result;
  }
  result.add(value.toString());
  return result;
}
