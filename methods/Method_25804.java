@Override public Description matchMethodInvocation(MethodInvocationTree methodInvocationTree,VisitorState state){
  Type calledMethodType=ASTHelpers.getType(methodInvocationTree.getMethodSelect());
  Type calledClazzType=ASTHelpers.getReceiverType(methodInvocationTree);
  List<? extends ExpressionTree> arguments=methodInvocationTree.getArguments();
  MethodSymbol declaredMethod=ASTHelpers.getSymbol(methodInvocationTree);
  if (arguments.isEmpty() || declaredMethod == null) {
    return Description.NO_MATCH;
  }
  List<RequiredType> requiredTypesAtCallSite=new ArrayList<>(Collections.nCopies(arguments.size(),null));
  Types types=state.getTypes();
  if (!populateTypesToEnforce(declaredMethod,calledMethodType,calledClazzType,requiredTypesAtCallSite,state)) {
    for (    MethodSymbol method : ASTHelpers.findSuperMethods(declaredMethod,types)) {
      if (populateTypesToEnforce(method,calledMethodType,calledClazzType,requiredTypesAtCallSite,state)) {
        break;
      }
    }
  }
  reportAnyViolations(arguments,requiredTypesAtCallSite,state);
  return Description.NO_MATCH;
}
