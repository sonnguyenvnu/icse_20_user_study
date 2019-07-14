private static boolean isQNFLiteral(Condition<?> condition){
  return condition.getType() == Condition.Type.LITERAL && (!(condition instanceof PredicateCondition) || ((PredicateCondition)condition).getPredicate().isQNF());
}
