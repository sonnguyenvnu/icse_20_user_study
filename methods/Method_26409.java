private static SuggestedFix generateFix(MethodInvocationTree tree,VisitorState state){
  NewClassTree receiver=(NewClassTree)getReceiver(tree);
  List<? extends ExpressionTree> arguments=receiver.getArguments();
  MethodSymbol methodSymbol=getSymbol(receiver);
  String unit=((MemberSelectTree)tree.getMethodSelect()).getIdentifier().toString().replace("get","");
  SuggestedFix.Builder fixBuilder=SuggestedFix.builder();
  if (isSameType(state.getTypes().unboxedTypeOrType(methodSymbol.params().get(0).type),state.getSymtab().longType,state)) {
    String duration=SuggestedFixes.qualifyType(state,fixBuilder,"org.joda.time.Duration");
    return fixBuilder.replace(tree,String.format("new %s(%s, %s).getStandard%s()",duration,state.getSourceForNode(arguments.get(0)),state.getSourceForNode(arguments.get(1)),unit)).build();
  }
  String unitImport=SuggestedFixes.qualifyType(state,fixBuilder,"org.joda.time." + unit);
  return fixBuilder.replace(tree,String.format("%s.%sBetween(%s, %s).get%s()",unitImport,unit.toLowerCase(),state.getSourceForNode(arguments.get(0)),state.getSourceForNode(arguments.get(1)),unit)).build();
}
