@Override public Description matchMethod(MethodTree tree,VisitorState state){
  Symbol sym=ASTHelpers.getSymbol(tree);
  if (sym == null) {
    return NO_MATCH;
  }
  if (!sym.isConstructor()) {
    return NO_MATCH;
  }
  AnnotationTree annotation=ASTHelpers.getAnnotationWithSimpleName(tree.getModifiers().getAnnotations(),"Nullable");
  if (annotation == null) {
    return NO_MATCH;
  }
  return describeMatch(annotation,SuggestedFix.delete(annotation));
}
