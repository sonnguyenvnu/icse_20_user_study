private static void visitTypeVarRefs(TypeVariableSymbol typeVar,Type declaredType,ArrayDeque<Integer> partialSelector,@Nullable Type inferredType,TypeComponentConsumer consumer){
  List<Type> declaredTypeArguments=declaredType.getTypeArguments();
  List<Type> inferredTypeArguments=inferredType != null ? inferredType.getTypeArguments() : ImmutableList.of();
  for (int i=0; i < declaredTypeArguments.size(); i++) {
    partialSelector.push(i);
    visitTypeVarRefs(typeVar,declaredTypeArguments.get(i),partialSelector,i < inferredTypeArguments.size() ? inferredTypeArguments.get(i) : null,consumer);
    partialSelector.pop();
  }
  if (declaredType.tsym.equals(typeVar)) {
    consumer.accept(declaredType,partialSelector,inferredType);
  }
}
