@SuppressWarnings("unchecked") public void setValue(Object object,Object value){
  if (map != null) {
    map.put(key,value);
    return;
  }
  if (collection != null) {
    collection.add(value);
    return;
  }
  list.set(index,value);
  if (list instanceof JSONArray) {
    JSONArray jsonArray=(JSONArray)list;
    Object array=jsonArray.getRelatedArray();
    if (array != null) {
      int arrayLength=Array.getLength(array);
      if (arrayLength > index) {
        Object item;
        if (jsonArray.getComponentType() != null) {
          item=TypeUtils.cast(value,jsonArray.getComponentType(),parser.getConfig());
        }
 else {
          item=value;
        }
        Array.set(array,index,item);
      }
    }
  }
}
