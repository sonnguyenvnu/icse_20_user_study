@Override public AccessPathStore<Nullness> initialStore(UnderlyingAST underlyingAST,List<LocalVariableNode> parameters){
  if (parameters == null) {
    return AccessPathStore.empty();
  }
  AccessPathStore.Builder<Nullness> result=AccessPathStore.<Nullness>empty().toBuilder();
  for (  LocalVariableNode param : parameters) {
    Nullness declared=NullnessAnnotations.fromAnnotationsOn((Symbol)param.getElement()).orElse(defaultAssumption);
    result.setInformation(AccessPath.fromLocalVariable(param),declared);
  }
  return result.build();
}
