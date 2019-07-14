private static Optional<TypeSymbol> protoType(ExpressionTree tree,VisitorState state){
  Symbol symbol=getSymbol(tree);
  if (symbol != null && symbol.owner != null && isSubtype(symbol.owner.type,MESSAGE.get(state),state)) {
    return Optional.of(symbol.owner.type.tsym);
  }
  return Optional.empty();
}
