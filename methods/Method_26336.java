@Override public Symbol resolveEnclosingClass(ExpressionTree expr){
  checkGuardedBy(expr instanceof IdentifierTree,"bad type literal: %s",expr);
  IdentifierTree ident=(IdentifierTree)expr;
  Symbol type=resolveType(ident.getName().toString(),SearchSuperTypes.NO);
  if (type instanceof Symbol.ClassSymbol) {
    return type;
  }
  return null;
}
