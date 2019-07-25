private void process(FieldsMetadata fieldsMetadata,String key,Class<?> clazz,List<PropertyDescriptor> path,boolean isList) throws IntrospectionException {
  BeanInfo infos=Introspector.getBeanInfo(clazz);
  PropertyDescriptor[] descs=infos.getPropertyDescriptors();
  for (  PropertyDescriptor propertyDescriptor : descs) {
    if (isTransient(propertyDescriptor,clazz) || !haveGetterMethod(propertyDescriptor))     continue;
    Method method=propertyDescriptor.getReadMethod();
    Class<?> returnTypeClass=method.getReturnType();
    boolean wasVisited=false;
    for (    PropertyDescriptor item : path) {
      if (item.equals(propertyDescriptor)) {
        wasVisited=true;
        break;
      }
    }
    if (wasVisited)     continue;
    if (filter.test(propertyDescriptor)) {
      return;
    }
    if (Iterable.class.isAssignableFrom(returnTypeClass)) {
      Type collectionType=method.getGenericReturnType();
      if (collectionType != null && (collectionType instanceof ParameterizedType)) {
        ParameterizedType parameterizedType=(ParameterizedType)method.getGenericReturnType();
        Type[] types=parameterizedType.getActualTypeArguments();
        if (types.length == 1) {
          Class<?> itemClazz=null;
          if (types[0] instanceof WildcardType) {
            WildcardType wildcardType=(WildcardType)types[0];
            if (wildcardType.getLowerBounds().length != 0) {
              itemClazz=(Class<?>)wildcardType.getLowerBounds()[0];
            }
 else             if (wildcardType.getUpperBounds().length != 0) {
              itemClazz=(Class<?>)wildcardType.getUpperBounds()[0];
            }
          }
 else {
            itemClazz=(Class<?>)types[0];
          }
          if (itemClazz != null) {
            if (isImageField(itemClazz)) {
              addField(key,fieldsMetadata,path,propertyDescriptor,true,true);
            }
 else             if (isTextField(itemClazz)) {
              addField(key,fieldsMetadata,path,propertyDescriptor,true,false);
            }
 else {
              path.add(propertyDescriptor);
              process(fieldsMetadata,key,itemClazz,path,true);
              path.remove(propertyDescriptor);
            }
          }
        }
      }
    }
 else     if (isImageField(returnTypeClass)) {
      addField(key,fieldsMetadata,path,propertyDescriptor,isList,true);
    }
 else     if (isTextField(returnTypeClass)) {
      addField(key,fieldsMetadata,path,propertyDescriptor,isList,false);
    }
 else {
      path.add(propertyDescriptor);
      process(fieldsMetadata,key,returnTypeClass,path,isList);
      path.remove(propertyDescriptor);
    }
  }
}
