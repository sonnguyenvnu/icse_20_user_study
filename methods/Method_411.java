@SuppressWarnings("rawtypes") protected void deepScan(final Object currentObject,final String propertyName,List<Object> results){
  if (currentObject == null) {
    return;
  }
  if (currentObject instanceof Map) {
    Map<?,?> map=(Map<?,?>)currentObject;
    for (    Map.Entry entry : map.entrySet()) {
      Object val=entry.getValue();
      if (propertyName.equals(entry.getKey())) {
        if (val instanceof Collection) {
          results.addAll((Collection)val);
        }
 else {
          results.add(val);
        }
        continue;
      }
      if (val == null || ParserConfig.isPrimitive2(val.getClass())) {
        continue;
      }
      deepScan(val,propertyName,results);
    }
    return;
  }
  if (currentObject instanceof Collection) {
    Iterator iterator=((Collection)currentObject).iterator();
    while (iterator.hasNext()) {
      Object next=iterator.next();
      if (ParserConfig.isPrimitive2(next.getClass())) {
        continue;
      }
      deepScan(next,propertyName,results);
    }
    return;
  }
  final Class<?> currentClass=currentObject.getClass();
  JavaBeanSerializer beanSerializer=getJavaBeanSerializer(currentClass);
  if (beanSerializer != null) {
    try {
      FieldSerializer fieldDeser=beanSerializer.getFieldSerializer(propertyName);
      if (fieldDeser != null) {
        try {
          Object val=fieldDeser.getPropertyValueDirect(currentObject);
          results.add(val);
        }
 catch (        InvocationTargetException ex) {
          throw new JSONException("getFieldValue error." + propertyName,ex);
        }
catch (        IllegalAccessException ex) {
          throw new JSONException("getFieldValue error." + propertyName,ex);
        }
        return;
      }
      List<Object> fieldValues=beanSerializer.getFieldValues(currentObject);
      for (      Object val : fieldValues) {
        deepScan(val,propertyName,results);
      }
      return;
    }
 catch (    Exception e) {
      throw new JSONPathException("jsonpath error, path " + path + ", segement " + propertyName,e);
    }
  }
  if (currentObject instanceof List) {
    List list=(List)currentObject;
    for (int i=0; i < list.size(); ++i) {
      Object val=list.get(i);
      deepScan(val,propertyName,results);
    }
    return;
  }
}
