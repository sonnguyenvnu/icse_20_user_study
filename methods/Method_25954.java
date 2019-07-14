private Description fixByModifyingMethod(VisitorState state,JCClassDecl enclosingClass,MethodTree method){
  return describeMatch(method,SuggestedFix.builder().addImport("dagger.Binds").merge(convertMethodToBinds(method,state)).merge(makeConcreteClassAbstract(enclosingClass,state)).build());
}
