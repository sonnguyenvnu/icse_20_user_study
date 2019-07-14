private static ImmutableSet<Symbol> getAnnotations(MethodSymbol symbol){
  return symbol.getRawAttributes().stream().map(a -> a.type.tsym).collect(toImmutableSet());
}
