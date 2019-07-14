public static <E extends JanusGraphElement>void traversal(Condition<E> condition,Predicate<Condition<E>> evaluator){
  Preconditions.checkArgument(!evaluator.apply(condition) || condition.getType() == Condition.Type.LITERAL || condition instanceof Not || condition instanceof MultiCondition,"Unexpected condition type: " + condition);
  if (condition instanceof Not) {
    traversal(((Not)condition).getChild(),evaluator);
  }
 else   if (condition instanceof MultiCondition) {
    condition.getChildren().forEach(child -> traversal(child,evaluator));
  }
}
