protected Object getSimpleProperty(final BeanProperty bp){
  if (bp.name.length() == 0) {
    if (bp.indexString != null) {
      return bp.bean;
    }
    throw new BeanException("Invalid property",bp);
  }
  Getter getter=bp.getGetter(isDeclared);
  if (getter != null) {
    Object result;
    try {
      result=getter.invokeGetter(bp.bean);
    }
 catch (    Exception ex) {
      if (isSilent) {
        return null;
      }
      throw new BeanException("Getter failed: " + getter,ex);
    }
    if ((result == null) && (isForced)) {
      result=createBeanProperty(bp);
    }
    return result;
  }
  if (bp.isMap()) {
    Map map=(Map)bp.bean;
    Object key=convertIndexToMapKey(getter,bp.name);
    if (!map.containsKey(key)) {
      if (!isForced) {
        if (isSilent) {
          return null;
        }
        throw new BeanException("Map key not found: " + bp.name,bp);
      }
      Map value=new HashMap();
      map.put(key,value);
      return value;
    }
    return map.get(key);
  }
  if (isSilent) {
    return null;
  }
  throw new BeanException("Simple property not found: " + bp.name,bp);
}
