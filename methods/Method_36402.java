private XAnnotatedMember createExtendFieldMember(Field field,Annotation annotation,XAnnotatedObject xob){
  XSetter setter=new XFieldSetter(field);
  XGetter getter=new XFieldGetter(field);
  return createExtendMember(annotation,setter,getter,xob);
}
