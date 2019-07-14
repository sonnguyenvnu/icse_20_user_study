public static <E extends JanusGraphElement>Condition<E> simplifyQNF(Condition<E> condition){
  Preconditions.checkArgument(isQueryNormalForm(condition));
  if (condition.numChildren() == 1) {
    final Condition<E> child=((And<E>)condition).get(0);
    if (child.getType() == Condition.Type.LITERAL)     return child;
  }
  return condition;
}
