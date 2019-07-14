@Override public Description matchMethod(MethodTree node,VisitorState state){
  Symbol.MethodSymbol method=ASTHelpers.getSymbol(node);
  if (method == null) {
    return Description.NO_MATCH;
  }
  List<Integer> compileTimeConstantAnnotationIndexes=new ArrayList<>();
  for (int i=0; i < method.getParameters().size(); i++) {
    if (hasCompileTimeConstantAnnotation(state,method.getParameters().get(i))) {
      compileTimeConstantAnnotationIndexes.add(i);
    }
  }
  if (compileTimeConstantAnnotationIndexes.isEmpty()) {
    return Description.NO_MATCH;
  }
  for (  Symbol.MethodSymbol superMethod : ASTHelpers.findSuperMethods(method,state.getTypes())) {
    for (    Integer index : compileTimeConstantAnnotationIndexes) {
      if (!hasCompileTimeConstantAnnotation(state,superMethod.getParameters().get(index))) {
        return buildDescription(node).setMessage("Method with @CompileTimeConstant parameter can't override method without it.").build();
      }
    }
  }
  return Description.NO_MATCH;
}
