private static Stream<MethodSymbol> findSuperMethods(MethodSymbol methodSymbol,Types types,boolean skipInterfaces){
  TypeSymbol owner=(TypeSymbol)methodSymbol.owner;
  return types.closure(owner.type).stream().filter(closureTypes -> skipInterfaces ? !closureTypes.isInterface() : true).map(type -> findSuperMethodInType(methodSymbol,type,types)).filter(Objects::nonNull);
}
