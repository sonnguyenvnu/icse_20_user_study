@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  MethodSymbol method=ASTHelpers.getSymbol(tree);
  if (method == null) {
    return Description.NO_MATCH;
  }
  Type currentClass=getOutermostClass(state);
  if (method.isStatic() || method.isConstructor() || currentClass == null) {
    return Description.NO_MATCH;
  }
  if (isSuperCall(currentClass,tree,state)) {
    MethodTree currentMethod=findDirectMethod(state.getPath());
    if (currentMethod != null) {
      if (currentMethod.getName().equals(method.name)) {
        MethodSymbol currentMethodSymbol=ASTHelpers.getSymbol(currentMethod);
        if (currentMethodSymbol.overrides(method,(TypeSymbol)method.owner,state.getTypes(),true)) {
          return Description.NO_MATCH;
        }
      }
    }
  }
  List<MethodSymbol> overriddenMethods=getOverriddenMethods(state,method);
  for (  Symbol overriddenMethod : overriddenMethods) {
    Type declaringClass=ASTHelpers.outermostClass(overriddenMethod).asType();
    if (!ASTHelpers.isSameType(declaringClass,currentClass,state)) {
      String customMessage=MESSAGE_BASE + "must not be invoked directly " + "(except by the declaring class, " + declaringClass + ")";
      return buildDescription(tree).setMessage(customMessage).build();
    }
  }
  return Description.NO_MATCH;
}
