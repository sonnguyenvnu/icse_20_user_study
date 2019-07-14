@Override public final Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  return this.methodExtractor.extract(tree,state).flatMap(type -> argFromClass(type,state)).map(type -> checkMockedType(type,tree,state)).orElse(NO_MATCH);
}
