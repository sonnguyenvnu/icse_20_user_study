private static Optional<Symbol> getBetterImport(TypeSymbol classSymbol,String simpleName){
  Symbol owner=classSymbol;
  long dots=simpleName.chars().filter(c -> c == '.').count();
  for (long i=0; i < dots + 1; ++i) {
    owner=owner.owner;
  }
  if (owner instanceof ClassSymbol) {
    return Optional.of(owner);
  }
  return Optional.empty();
}
