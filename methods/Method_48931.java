private static boolean isQNFLiteralOrNot(Condition<?> condition){
  if (!(condition instanceof Not)) {
    return isQNFLiteral(condition);
  }
  final Condition child=((Not)condition).getChild();
  return isQNFLiteral(child) && (!(child instanceof PredicateCondition) || !((PredicateCondition)child).getPredicate().hasNegation());
}
