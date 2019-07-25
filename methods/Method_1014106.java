/** 
 * Creates a repeating expression that will match the given expression as often as possible. Only succeeds, if there is at least one match. The resulting node's value will be an  {@link Object[]} that contains all values of thematches.
 * @param expression the repeating expression
 * @return resulting expression
 */
protected ExpressionCardinality plus(Object expression){
  return new ExpressionCardinality(exp(expression),true,false);
}
