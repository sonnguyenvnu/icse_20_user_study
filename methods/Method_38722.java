/** 
 * Converts type of all list elements to match the component type.
 */
private Object generifyList(final List list,final Class componentType){
  for (int i=0; i < list.size(); i++) {
    Object element=list.get(i);
    if (element != null) {
      if (element instanceof Map) {
        Object bean=map2bean((Map)element,componentType);
        list.set(i,bean);
      }
 else {
        Object value=convert(element,componentType);
        list.set(i,value);
      }
    }
  }
  return list;
}
