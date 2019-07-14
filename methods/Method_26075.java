private Description generateDescription(Map<MethodSymbol,MethodDetails> nodes,VisitorState state){
  SuggestedFix.Builder fixBuilder=SuggestedFix.builder();
  Set<MethodTree> affectedTrees=new HashSet<>();
  for (  Map.Entry<MethodSymbol,MethodDetails> entry : nodes.entrySet()) {
    MethodSymbol sym=entry.getKey();
    MethodDetails methodDetails=entry.getValue();
    boolean noExternalMethods=!referencesExternalMethods(methodDetails,nodes.keySet());
    if (methodDetails.couldPossiblyBeStatic && noExternalMethods) {
      addModifiers(methodDetails.tree,state,Modifier.STATIC).map(f -> fixQualifiers(state,sym,f)).ifPresent(fixBuilder::merge);
      affectedTrees.add(methodDetails.tree);
    }
  }
  return findingOutputStyle.report(affectedTrees,fixBuilder.build(),state,this);
}
