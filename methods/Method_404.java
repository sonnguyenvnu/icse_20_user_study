@SuppressWarnings({"unchecked","rawtypes"}) public boolean setArrayItem(JSONPath path,Object currentObject,int index,Object value){
  if (currentObject instanceof List) {
    List list=(List)currentObject;
    if (index >= 0) {
      list.set(index,value);
    }
 else {
      list.set(list.size() + index,value);
    }
    return true;
  }
  Class<?> clazz=currentObject.getClass();
  if (clazz.isArray()) {
    int arrayLenth=Array.getLength(currentObject);
    if (index >= 0) {
      if (index < arrayLenth) {
        Array.set(currentObject,index,value);
      }
    }
 else {
      if (Math.abs(index) <= arrayLenth) {
        Array.set(currentObject,arrayLenth + index,value);
      }
    }
    return true;
  }
  throw new JSONPathException("unsupported set operation." + clazz);
}
