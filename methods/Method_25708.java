private static ImmutableList<VarSymbol> getFormalParametersWithoutVarArgs(MethodSymbol invokedMethodSymbol){
  List<VarSymbol> formalParameters=invokedMethodSymbol.getParameters();
  if (!formalParameters.isEmpty() && formalParameters.get(0).getSimpleName().toString().matches("this\\$[0-9]+")) {
    return ImmutableList.of();
  }
  int size=invokedMethodSymbol.isVarArgs() ? formalParameters.size() - 1 : formalParameters.size();
  return ImmutableList.copyOf(formalParameters.subList(0,size));
}
