private static Symbol getFinalizer(VisitorState state,ClassSymbol enclosing){
  Type finalizerType=state.getTypeFromString("com.google.common.labs.base.Finalizer");
  Optional<VarSymbol> finalizerField=state.getTypes().closure(enclosing.asType()).stream().flatMap(s -> getFields(s.asElement())).filter(s -> ASTHelpers.isSameType(finalizerType,s.asType(),state)).findFirst();
  if (finalizerField.isPresent()) {
    return finalizerField.get();
  }
  return ASTHelpers.resolveExistingMethod(state,enclosing.enclClass(),state.getName("finalize"),ImmutableList.of(),ImmutableList.of());
}
