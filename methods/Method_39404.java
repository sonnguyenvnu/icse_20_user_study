/** 
 * Resolves all collections for given type.
 */
public SetInjectionPoint[] resolve(final Class type,final boolean autowire){
  ClassDescriptor cd=ClassIntrospector.get().lookup(type);
  List<SetInjectionPoint> list=new ArrayList<>();
  PropertyDescriptor[] allProperties=cd.getAllPropertyDescriptors();
  for (  PropertyDescriptor propertyDescriptor : allProperties) {
    if (propertyDescriptor.isGetterOnly()) {
      continue;
    }
    Class propertyType=propertyDescriptor.getType();
    if (!ClassUtil.isTypeOf(propertyType,Collection.class)) {
      continue;
    }
    MethodDescriptor writeMethodDescriptor=propertyDescriptor.getWriteMethodDescriptor();
    FieldDescriptor fieldDescriptor=propertyDescriptor.getFieldDescriptor();
    PetiteInject ref=null;
    if (writeMethodDescriptor != null) {
      ref=writeMethodDescriptor.getMethod().getAnnotation(PetiteInject.class);
    }
    if (ref == null && fieldDescriptor != null) {
      ref=fieldDescriptor.getField().getAnnotation(PetiteInject.class);
    }
    if ((!autowire) && (ref == null)) {
      continue;
    }
    list.add(new SetInjectionPoint(propertyDescriptor));
  }
  SetInjectionPoint[] fields;
  if (list.isEmpty()) {
    fields=SetInjectionPoint.EMPTY;
  }
 else {
    fields=list.toArray(new SetInjectionPoint[0]);
  }
  return fields;
}
