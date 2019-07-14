private static Predicate<JCVariableDecl> isCollectionVariable(final VisitorState state){
  return var -> variableType(isSubtypeOf("java.util.Collection")).matches(var,state);
}
