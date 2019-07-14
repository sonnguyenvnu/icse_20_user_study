private XAnnotatedMember createMember(Annotation annotation,XSetter setter,XGetter getter){
  XAnnotatedMember member=null;
  int type=annotation.annotationType().getAnnotation(XMemberAnnotation.class).value();
  if (type == XMemberAnnotation.NODE) {
    member=new XAnnotatedMember(this,setter,getter,(XNode)annotation);
  }
 else   if (type == XMemberAnnotation.NODE_LIST) {
    member=new XAnnotatedList(this,setter,getter,(XNodeList)annotation);
  }
 else   if (type == XMemberAnnotation.NODE_MAP) {
    member=new XAnnotatedMap(this,setter,getter,(XNodeMap)annotation);
  }
 else   if (type == XMemberAnnotation.PARENT) {
    member=new XAnnotatedParent(this,setter,getter);
  }
 else   if (type == XMemberAnnotation.CONTENT) {
    member=new XAnnotatedContent(this,setter,getter,(XContent)annotation);
  }
  return member;
}
