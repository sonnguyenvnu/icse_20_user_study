private static Optional<TypeSymbol> protoType(Tree tree,VisitorState state){
  Symbol symbol=getSymbol(tree);
  if (symbol != null && symbol.owner instanceof ClassSymbol && isSubtype(symbol.owner.type,MESSAGE.get(state),state)) {
    return Optional.of(symbol.owner.type.tsym);
  }
  return Optional.empty();
}
