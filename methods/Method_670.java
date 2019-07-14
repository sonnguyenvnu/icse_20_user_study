@SuppressWarnings("unchecked") private <T>T toObjectArray(DefaultJSONParser parser,Class<?> componentType,JSONArray array){
  if (array == null) {
    return null;
  }
  int size=array.size();
  Object objArray=Array.newInstance(componentType,size);
  for (int i=0; i < size; ++i) {
    Object value=array.get(i);
    if (value == array) {
      Array.set(objArray,i,objArray);
      continue;
    }
    if (componentType.isArray()) {
      Object element;
      if (componentType.isInstance(value)) {
        element=value;
      }
 else {
        element=toObjectArray(parser,componentType,(JSONArray)value);
      }
      Array.set(objArray,i,element);
    }
 else {
      Object element=null;
      if (value instanceof JSONArray) {
        boolean contains=false;
        JSONArray valueArray=(JSONArray)value;
        int valueArraySize=valueArray.size();
        for (int y=0; y < valueArraySize; ++y) {
          Object valueItem=valueArray.get(y);
          if (valueItem == array) {
            valueArray.set(i,objArray);
            contains=true;
          }
        }
        if (contains) {
          element=valueArray.toArray();
        }
      }
      if (element == null) {
        element=TypeUtils.cast(value,componentType,parser.getConfig());
      }
      Array.set(objArray,i,element);
    }
  }
  array.setRelatedArray(objArray);
  array.setComponentType(componentType);
  return (T)objArray;
}
