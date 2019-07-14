/** 
 * Resolves all properties for given type.
 */
public PropertyInjectionPoint[] resolve(Class type,final boolean autowire){
  final List<PropertyInjectionPoint> list=new ArrayList<>();
  final Set<String> usedPropertyNames=new HashSet<>();
  while (type != Object.class) {
    final ClassDescriptor cd=ClassIntrospector.get().lookup(type);
    final PropertyDescriptor[] allPropertyDescriptors=cd.getAllPropertyDescriptors();
    for (    PropertyDescriptor propertyDescriptor : allPropertyDescriptors) {
      if (propertyDescriptor.isGetterOnly()) {
        continue;
      }
      if (usedPropertyNames.contains(propertyDescriptor.getName())) {
        continue;
      }
      Class propertyType=propertyDescriptor.getType();
      if (ClassUtil.isTypeOf(propertyType,Collection.class)) {
        continue;
      }
      BeanReferences reference=referencesResolver.readReferenceFromAnnotation(propertyDescriptor);
      if (reference == null) {
        if (!autowire) {
          continue;
        }
 else {
          reference=referencesResolver.buildDefaultReference(propertyDescriptor);
        }
      }
      list.add(new PropertyInjectionPoint(propertyDescriptor,reference));
      usedPropertyNames.add(propertyDescriptor.getName());
    }
    type=type.getSuperclass();
  }
  final PropertyInjectionPoint[] fields;
  if (list.isEmpty()) {
    fields=PropertyInjectionPoint.EMPTY;
  }
 else {
    fields=list.toArray(new PropertyInjectionPoint[0]);
  }
  return fields;
}
