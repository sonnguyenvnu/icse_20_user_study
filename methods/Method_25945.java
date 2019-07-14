@Override public Description matchMethod(MethodTree method,VisitorState state){
  if (!CAN_BE_A_MULTIBINDS_METHOD.matches(method,state)) {
    return NO_MATCH;
  }
  JCClassDecl enclosingClass=ASTHelpers.findEnclosingNode(state.getPath(),JCClassDecl.class);
  for (  JCAnnotation annotation : enclosingClass.getModifiers().getAnnotations()) {
    if (ASTHelpers.getSymbol(annotation.getAnnotationType()).getQualifiedName().contentEquals("dagger.Module") && HAS_DAGGER_ONE_MODULE_ARGUMENT.matches(annotation,state)) {
      return NO_MATCH;
    }
  }
  return fixByModifyingMethod(state,enclosingClass,method);
}
