public final XAnnotatedMember createExtendMethodMember(Method method,Annotation annotation,XAnnotatedObject xob){
  XSetter setter=new XMethodSetter(method);
  XGetter getter=new XMethodGetter(null,null,null);
  return createExtendMember(annotation,setter,getter,xob);
}
