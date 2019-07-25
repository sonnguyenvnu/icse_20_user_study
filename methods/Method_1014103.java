/** 
 * Creates a sequence expression. Matches, if all the given expressions match. They are tested in the provided order. The resulting nodes's value will be an  {@link Object[]} that contains all values of thematching expressions.
 * @param expressions the expressions (alternatives) that have to match in sequence
 * @return resulting expression
 */
protected ExpressionSequence seq(Object... expressions){
  return new ExpressionSequence(exps(expressions));
}
