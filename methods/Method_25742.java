private static MethodSymbol getMatchingMethod(Type type,Name name,Predicate<MethodSymbol> predicate){
  Scope scope=type.tsym.members();
  for (  Symbol sym : scope.getSymbolsByName(name)) {
    if (!(sym instanceof MethodSymbol)) {
      continue;
    }
    MethodSymbol methodSymbol=(MethodSymbol)sym;
    if (predicate.apply(methodSymbol)) {
      return methodSymbol;
    }
  }
  return null;
}
