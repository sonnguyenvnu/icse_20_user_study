/** 
 * Prevents abstract methods from being annotated with  {@code @CallSuper} et al. It doesn't makesense to require overriders to call a method with no implementation.
 */
@Override public Description matchAnnotation(AnnotationTree tree,VisitorState state){
  if (!ANNOTATION_MATCHER.matches(tree,state)) {
    return Description.NO_MATCH;
  }
  MethodTree methodTree=ASTHelpers.findEnclosingNode(state.getPath(),MethodTree.class);
  if (methodTree == null) {
    return Description.NO_MATCH;
  }
  MethodSymbol methodSym=ASTHelpers.getSymbol(methodTree);
  if (methodSym == null) {
    return Description.NO_MATCH;
  }
  if (!methodSym.getModifiers().contains(Modifier.ABSTRACT)) {
    return Description.NO_MATCH;
  }
  Symbol annotationSym=ASTHelpers.getSymbol(tree);
  if (annotationSym == null) {
    return Description.NO_MATCH;
  }
  return buildDescription(tree).setMessage(String.format("@%s cannot be applied to an abstract method",annotationSym.getSimpleName())).build();
}
