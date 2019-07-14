/** 
 * Resolve method injection points in given class.
 */
public MethodInjectionPoint[] resolve(final Class type){
  ClassDescriptor cd=ClassIntrospector.get().lookup(type);
  List<MethodInjectionPoint> list=new ArrayList<>();
  MethodDescriptor[] allMethods=cd.getAllMethodDescriptors();
  for (  MethodDescriptor methodDescriptor : allMethods) {
    Method method=methodDescriptor.getMethod();
    if (ClassUtil.isBeanPropertySetter(method)) {
      continue;
    }
    if (method.getParameterTypes().length == 0) {
      continue;
    }
    BeanReferences[] references=referencesResolver.readAllReferencesFromAnnotation(method);
    if (references != null) {
      MethodInjectionPoint methodInjectionPoint=new MethodInjectionPoint(method,references);
      list.add(methodInjectionPoint);
    }
  }
  final MethodInjectionPoint[] methodInjectionPoints;
  if (list.isEmpty()) {
    methodInjectionPoints=MethodInjectionPoint.EMPTY;
  }
 else {
    methodInjectionPoints=list.toArray(new MethodInjectionPoint[0]);
  }
  return methodInjectionPoints;
}
