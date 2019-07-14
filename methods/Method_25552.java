private static boolean hasSimpleName(AnnotationTree annotation,String name){
  Tree annotationType=annotation.getAnnotationType();
  javax.lang.model.element.Name simpleName;
  if (annotationType instanceof IdentifierTree) {
    simpleName=((IdentifierTree)annotationType).getName();
  }
 else   if (annotationType instanceof MemberSelectTree) {
    simpleName=((MemberSelectTree)annotationType).getIdentifier();
  }
 else {
    return false;
  }
  return simpleName.contentEquals(name);
}
