public final XAnnotatedMember createMethodMember(Method method,Class<?> clazz,Annotation annotation){
  XSetter setter=new XMethodSetter(method);
  String methodName=method.getName();
  String name=Introspector.decapitalize(methodName.substring(3));
  XGetter getter=new XMethodGetter(getGetterMethod(clazz,name),clazz,name);
  return createMember(annotation,setter,getter);
}
