@Override public Description matchNewClass(NewClassTree tree,VisitorState state){
  if (!NEW_THREAD_LOCAL.matches(tree,state)) {
    return NO_MATCH;
  }
  if (wellKnownTypeArgument(tree,state)) {
    return NO_MATCH;
  }
  Tree parent=state.getPath().getParentPath().getLeaf();
  if (!(parent instanceof VariableTree)) {
    return NO_MATCH;
  }
  VarSymbol sym=getSymbol((VariableTree)parent);
  if (sym != null && sym.isStatic()) {
    return NO_MATCH;
  }
  if (Streams.stream(state.getPath()).filter(ClassTree.class::isInstance).map(ClassTree.class::cast).anyMatch(c -> {
    if (hasDirectAnnotationWithSimpleName(getSymbol(c),"Singleton")) {
      return true;
    }
    Type scopeType=state.getTypeFromString("com.google.inject.Scope");
    if (isSubtype(getType(c),scopeType,state)) {
      return true;
    }
    return false;
  }
)) {
    return NO_MATCH;
  }
  return describeMatch(tree);
}
