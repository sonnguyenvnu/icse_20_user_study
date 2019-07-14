@Nullable private static FactoryMethodName tryFindFactory(MethodInvocationTree assertThatCall,VisitorState state){
  MethodSymbol assertThatSymbol=getSymbol(assertThatCall);
  if (assertThatSymbol == null) {
    return null;
  }
  if (assertThatSymbol.owner.getQualifiedName().contentEquals(PROTO_TRUTH_CLASS)) {
    return FactoryMethodName.create(PROTO_TRUTH_CLASS,"protos");
  }
  ImmutableSet<MethodSymbol> factories=concat(assertThatSymbol.owner.getEnclosedElements().stream(),assertThatSymbol.getReturnType().asElement().getEnclosedElements().stream()).filter(s -> s instanceof MethodSymbol).map(s -> (MethodSymbol)s).filter(s -> returns(s,SUBJECT_FACTORY_CLASS,state) || returns(s,CUSTOM_SUBJECT_BUILDER_FACTORY_CLASS,state)).collect(toImmutableSet());
  return factories.size() == 1 ? FactoryMethodName.tryCreate(getOnlyElement(factories)) : null;
}
