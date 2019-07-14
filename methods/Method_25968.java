private boolean isInjectedConstructor(MethodTree methodTree,VisitorState state){
  return allOf(methodIsConstructor(),hasAnnotation(GUICE_INJECT_ANNOTATION)).matches(methodTree,state);
}
