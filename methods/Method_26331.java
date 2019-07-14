@Override public Symbol resolveTypeLiteral(ExpressionTree expr){
  checkGuardedBy(expr instanceof IdentifierTree,"bad type literal: %s",expr);
  IdentifierTree ident=(IdentifierTree)expr;
  Symbol type=resolveType(ident.getName().toString(),SearchSuperTypes.YES);
  if (type instanceof Symbol.ClassSymbol) {
    return type;
  }
  return null;
}
