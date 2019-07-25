/** 
 * Creates an optional expression. Always succeeds. The resulting nodes's value will be the one of the matching expression or null.
 * @param expression the optionally matching expression
 * @return resulting expression
 */
protected ExpressionCardinality opt(Object expression){
  return new ExpressionCardinality(exp(expression),false,true);
}
