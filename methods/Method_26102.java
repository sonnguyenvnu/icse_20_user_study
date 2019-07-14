/** 
 * Matches a method that overrides a method that has been annotated with  {@code @CallSuper} etal., but does not call the super method.
 */
@Override public Description matchMethod(MethodTree tree,VisitorState state){
  MethodSymbol methodSym=ASTHelpers.getSymbol(tree);
  if (methodSym == null) {
    return Description.NO_MATCH;
  }
  if (methodSym.getModifiers().contains(Modifier.ABSTRACT)) {
    return Description.NO_MATCH;
  }
  String annotatedSuperMethod=null;
  String matchedAnnotationSimpleName=null;
  for (  MethodSymbol method : ASTHelpers.findSuperMethods(methodSym,state.getTypes())) {
    for (    AnnotationType annotationType : AnnotationType.values()) {
      if (ASTHelpers.hasAnnotation(method,annotationType.fullyQualifiedName(),state)) {
        annotatedSuperMethod=getMethodName(method);
        matchedAnnotationSimpleName=annotationType.simpleName();
        break;
      }
    }
  }
  if (annotatedSuperMethod == null || matchedAnnotationSimpleName == null) {
    return Description.NO_MATCH;
  }
  TreeScanner<Boolean,Void> findSuper=new FindSuperTreeScanner(tree.getName().toString());
  if (findSuper.scan(tree,null)) {
    return Description.NO_MATCH;
  }
  return buildDescription(tree).setMessage(String.format("This method overrides %s, which is annotated with @%s, but does not call the " + "super method",annotatedSuperMethod,matchedAnnotationSimpleName)).build();
}
