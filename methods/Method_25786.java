private Description evaluateCallers(MethodSymbol symbol){
  List<VariableTree> paramTypes=paramTypesForMethod.get(symbol);
  if (paramTypes == null) {
    return NO_MATCH;
  }
  for (  Caller caller : callersToEvaluate.removeAll(symbol)) {
    VisitorState state=caller.state;
    MethodInvocationTree invocation=caller.tree;
    MethodTree callerConstructor=state.findEnclosing(MethodTree.class);
    if (callerConstructor == null) {
      continue;
    }
    Map<String,Type> availableParams=indexTypeByName(callerConstructor.getParameters());
    for (int i=0; i < paramTypes.size() && i < invocation.getArguments().size(); i++) {
      VariableTree formalParam=paramTypes.get(i);
      String formalParamName=formalParam.getName().toString();
      Type formalParamType=getType(formalParam.getType());
      Type availableParamType=availableParams.get(formalParamName);
      ExpressionTree actualParam=invocation.getArguments().get(i);
      if (availableParamType == null || formalParamType == null || referencesIdentifierWithName(formalParamName,actualParam,state)) {
        continue;
      }
      if (state.getTypes().isAssignable(availableParamType,formalParamType)) {
        reportMatch(invocation,state,actualParam,formalParamName);
      }
    }
  }
  return NO_MATCH;
}
