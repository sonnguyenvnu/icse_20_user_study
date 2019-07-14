private static Optional<ComparisonSite> getDubiousComparison(ClassSymbol encl,Tree tree,ExpressionTree lhs,ExpressionTree rhs){
  Symbol lhsSymbol=getSymbol(lhs);
  Symbol rhsSymbol=getSymbol(rhs);
  if (lhsSymbol == null || rhsSymbol == null || lhsSymbol.equals(rhsSymbol)) {
    return Optional.empty();
  }
  if (lhsSymbol.isStatic() || rhsSymbol.isStatic()) {
    return Optional.empty();
  }
  if (!encl.equals(lhsSymbol.enclClass()) || !encl.equals(rhsSymbol.enclClass())) {
    return Optional.empty();
  }
  if (!FIELD_TYPES.contains(lhsSymbol.getKind()) || !FIELD_TYPES.contains(rhsSymbol.getKind())) {
    return Optional.empty();
  }
  if (getKind(lhs) != getKind(rhs)) {
    return Optional.empty();
  }
  return Optional.of(ComparisonSite.of(tree,lhsSymbol,rhsSymbol));
}
