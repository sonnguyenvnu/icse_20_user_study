@SuppressWarnings({"unchecked"}) private void _setIndexProperty(final BeanProperty bp,Object value){
  if (bp.indexString == null) {
    setSimpleProperty(bp,value);
    return;
  }
  Object nextBean=getSimpleProperty(bp);
  Getter getter=bp.getGetter(isDeclared);
  if (nextBean == null) {
    if (isSilent) {
      return;
    }
    throw new BeanException("Index property is null:" + bp.name,bp);
  }
  if (nextBean.getClass().isArray()) {
    int index=parseInt(bp.indexString,bp);
    if (isForced) {
      arrayForcedSet(bp,nextBean,index,value);
    }
 else {
      Array.set(nextBean,index,value);
    }
    return;
  }
  if (nextBean instanceof List) {
    int index=parseInt(bp.indexString,bp);
    Class listComponentType=extractGenericComponentType(getter);
    if (listComponentType != Object.class) {
      value=convertType(value,listComponentType);
    }
    List list=(List)nextBean;
    if (isForced) {
      ensureListSize(list,index);
    }
    list.set(index,value);
    return;
  }
  if (nextBean instanceof Map) {
    Map map=(Map)nextBean;
    Object key=convertIndexToMapKey(getter,bp.indexString);
    Class mapComponentType=extractGenericComponentType(getter);
    if (mapComponentType != Object.class) {
      value=convertType(value,mapComponentType);
    }
    map.put(key,value);
    return;
  }
  if (isSilent) {
    return;
  }
  throw new BeanException("Index property is not an array, list or map: " + bp.name,bp);
}
