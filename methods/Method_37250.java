private Object _getIndexProperty(final BeanProperty bp){
  Object resultBean=getSimpleProperty(bp);
  Getter getter=bp.getGetter(isDeclared);
  if (bp.indexString == null) {
    return resultBean;
  }
  if (resultBean == null) {
    if (isSilent) {
      return null;
    }
    throw new BeanException("Index property is null: " + bp.name,bp);
  }
  if (resultBean.getClass().isArray()) {
    int index=parseInt(bp.indexString,bp);
    if (isForced) {
      return arrayForcedGet(bp,resultBean,index);
    }
 else {
      return Array.get(resultBean,index);
    }
  }
  if (resultBean instanceof List) {
    int index=parseInt(bp.indexString,bp);
    List list=(List)resultBean;
    if (!isForced) {
      return list.get(index);
    }
    if (!bp.last) {
      ensureListSize(list,index);
    }
    Object value=list.get(index);
    if (value == null) {
      Class listComponentType=extractGenericComponentType(getter);
      if (listComponentType == Object.class) {
        listComponentType=Map.class;
      }
      try {
        value=ClassUtil.newInstance(listComponentType);
      }
 catch (      Exception ex) {
        if (isSilent) {
          return null;
        }
        throw new BeanException("Invalid list element: " + bp.name + '[' + index + ']',bp,ex);
      }
      list.set(index,value);
    }
    return value;
  }
  if (resultBean instanceof Map) {
    Map map=(Map)resultBean;
    Object key=convertIndexToMapKey(getter,bp.indexString);
    if (!isForced) {
      return map.get(key);
    }
    Object value=map.get(key);
    if (!bp.last) {
      if (value == null) {
        Class mapComponentType=extractGenericComponentType(getter);
        if (mapComponentType == Object.class) {
          mapComponentType=Map.class;
        }
        try {
          value=ClassUtil.newInstance(mapComponentType);
        }
 catch (        Exception ex) {
          if (isSilent) {
            return null;
          }
          throw new BeanException("Invalid map element: " + bp.name + '[' + bp.indexString + ']',bp,ex);
        }
        map.put(key,value);
      }
    }
    return value;
  }
  if (isSilent) {
    return null;
  }
  throw new BeanException("Index property is not an array, list or map: " + bp.name,bp);
}
