/** 
 * Inspects all properties of target type.
 */
protected HashMap<String,PropertyDescriptor> inspectProperties(){
  boolean scanAccessible=classDescriptor.isScanAccessible();
  Class type=classDescriptor.getType();
  HashMap<String,PropertyDescriptor> map=new HashMap<>();
  Method[] methods=scanAccessible ? ClassUtil.getAccessibleMethods(type) : ClassUtil.getSupportedMethods(type);
  for (int iteration=0; iteration < 2; iteration++) {
    for (    Method method : methods) {
      if (Modifier.isStatic(method.getModifiers())) {
        continue;
      }
      boolean add=false;
      boolean issetter=false;
      String propertyName;
      if (iteration == 0) {
        propertyName=ClassUtil.getBeanPropertyGetterName(method);
        if (propertyName != null) {
          add=true;
          issetter=false;
        }
      }
 else {
        propertyName=ClassUtil.getBeanPropertySetterName(method);
        if (propertyName != null) {
          add=true;
          issetter=true;
        }
      }
      if (add) {
        MethodDescriptor methodDescriptor=classDescriptor.getMethodDescriptor(method.getName(),method.getParameterTypes(),true);
        addProperty(map,propertyName,methodDescriptor,issetter);
      }
    }
  }
  if (classDescriptor.isIncludeFieldsAsProperties()) {
    FieldDescriptor[] fieldDescriptors=classDescriptor.getAllFieldDescriptors();
    String[] prefix=classDescriptor.getPropertyFieldPrefix();
    for (    FieldDescriptor fieldDescriptor : fieldDescriptors) {
      Field field=fieldDescriptor.getField();
      if (Modifier.isStatic(field.getModifiers())) {
        continue;
      }
      String name=field.getName();
      if (prefix != null) {
        for (        String p : prefix) {
          if (!name.startsWith(p)) {
            continue;
          }
          name=name.substring(p.length());
          break;
        }
      }
      if (!map.containsKey(name)) {
        map.put(name,createPropertyDescriptor(name,fieldDescriptor));
      }
    }
  }
  return map;
}
