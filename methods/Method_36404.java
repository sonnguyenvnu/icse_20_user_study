private XAnnotatedMember createExtendMember(Annotation annotation,XSetter setter,XGetter getter,XAnnotatedObject xob){
  XAnnotatedMember member=null;
  int type=annotation.annotationType().getAnnotation(XMemberAnnotation.class).value();
  if (type == XMemberAnnotation.NODE_SPRING) {
    member=new XAnnotatedSpring(this,setter,getter,(XNodeSpring)annotation,(XAnnotatedSpringObject)xob);
  }
 else   if (type == XMemberAnnotation.NODE_LIST_SPRING) {
    member=new XAnnotatedListSpring(this,setter,getter,(XNodeListSpring)annotation,(XAnnotatedSpringObject)xob);
  }
 else   if (type == XMemberAnnotation.NODE_MAP_SPRING) {
    member=new XAnnotatedMapSpring(this,setter,getter,(XNodeMapSpring)annotation,(XAnnotatedSpringObject)xob);
  }
  return member;
}
