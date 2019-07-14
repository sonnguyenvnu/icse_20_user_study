protected Object getPropertyValue(Object currentObject,String propertyName,long propertyNameHash){
  if (currentObject == null) {
    return null;
  }
  if (currentObject instanceof String) {
    try {
      JSONObject object=JSON.parseObject((String)currentObject);
      currentObject=object;
    }
 catch (    Exception ex) {
    }
  }
  if (currentObject instanceof Map) {
    Map map=(Map)currentObject;
    Object val=map.get(propertyName);
    if (val == null && (SIZE == propertyNameHash || LENGTH == propertyNameHash)) {
      val=map.size();
    }
    return val;
  }
  final Class<?> currentClass=currentObject.getClass();
  JavaBeanSerializer beanSerializer=getJavaBeanSerializer(currentClass);
  if (beanSerializer != null) {
    try {
      return beanSerializer.getFieldValue(currentObject,propertyName,propertyNameHash,false);
    }
 catch (    Exception e) {
      throw new JSONPathException("jsonpath error, path " + path + ", segement " + propertyName,e);
    }
  }
  if (currentObject instanceof List) {
    List list=(List)currentObject;
    if (SIZE == propertyNameHash || LENGTH == propertyNameHash) {
      return list.size();
    }
    List<Object> fieldValues=null;
    for (int i=0; i < list.size(); ++i) {
      Object obj=list.get(i);
      if (obj == list) {
        if (fieldValues == null) {
          fieldValues=new JSONArray(list.size());
        }
        fieldValues.add(obj);
        continue;
      }
      Object itemValue=getPropertyValue(obj,propertyName,propertyNameHash);
      if (itemValue instanceof Collection) {
        Collection collection=(Collection)itemValue;
        if (fieldValues == null) {
          fieldValues=new JSONArray(list.size());
        }
        fieldValues.addAll(collection);
      }
 else       if (itemValue != null) {
        if (fieldValues == null) {
          fieldValues=new JSONArray(list.size());
        }
        fieldValues.add(itemValue);
      }
    }
    if (fieldValues == null) {
      fieldValues=Collections.emptyList();
    }
    return fieldValues;
  }
  if (currentObject instanceof Object[]) {
    Object[] array=(Object[])currentObject;
    if (SIZE == propertyNameHash || LENGTH == propertyNameHash) {
      return array.length;
    }
    List<Object> fieldValues=new JSONArray(array.length);
    for (int i=0; i < array.length; ++i) {
      Object obj=array[i];
      if (obj == array) {
        fieldValues.add(obj);
        continue;
      }
      Object itemValue=getPropertyValue(obj,propertyName,propertyNameHash);
      if (itemValue instanceof Collection) {
        Collection collection=(Collection)itemValue;
        fieldValues.addAll(collection);
      }
 else       if (itemValue != null) {
        fieldValues.add(itemValue);
      }
    }
    return fieldValues;
  }
  if (currentObject instanceof Enum) {
    final long NAME=0xc4bcadba8e631b86L;
    final long ORDINAL=0xf1ebc7c20322fc22L;
    Enum e=(Enum)currentObject;
    if (NAME == propertyNameHash) {
      return e.name();
    }
    if (ORDINAL == propertyNameHash) {
      return e.ordinal();
    }
  }
  if (currentObject instanceof Calendar) {
    final long YEAR=0x7c64634977425edcL;
    final long MONTH=0xf4bdc3936faf56a5L;
    final long DAY=0xca8d3918f4578f1dL;
    final long HOUR=0x407efecc7eb5764fL;
    final long MINUTE=0x5bb2f9bdf2fad1e9L;
    final long SECOND=0xa49985ef4cee20bdL;
    Calendar e=(Calendar)currentObject;
    if (YEAR == propertyNameHash) {
      return e.get(Calendar.YEAR);
    }
    if (MONTH == propertyNameHash) {
      return e.get(Calendar.MONTH);
    }
    if (DAY == propertyNameHash) {
      return e.get(Calendar.DAY_OF_MONTH);
    }
    if (HOUR == propertyNameHash) {
      return e.get(Calendar.HOUR_OF_DAY);
    }
    if (MINUTE == propertyNameHash) {
      return e.get(Calendar.MINUTE);
    }
    if (SECOND == propertyNameHash) {
      return e.get(Calendar.SECOND);
    }
  }
  return null;
}
