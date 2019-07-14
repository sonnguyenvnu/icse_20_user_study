public final XAnnotatedMember createFieldMember(Field field,Annotation annotation){
  XSetter setter=new XFieldSetter(field);
  XGetter getter=new XFieldGetter(field);
  return createMember(annotation,setter,getter);
}
