/** 
 * Get inferred nullness qualifier for an expression, if possible. 
 */
public Optional<Nullness> getExprNullness(ExpressionTree exprTree){
  InferenceVariable iv=TypeArgInferenceVar.create(ImmutableList.of(),exprTree);
  return constraintGraph.nodes().contains(iv) ? getNullness(iv) : Optional.empty();
}
