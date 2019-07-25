/** 
 * Creates an alternatives expression. Matches, as soon as one of the given expressions matches. They are tested in the provided order. The value of the matching expression will be used for the resulting nodes's value.
 * @param expressions the expressions (alternatives) that are to be tested
 * @return resulting expression
 */
protected ExpressionAlternatives alt(Object... expressions){
  return new ExpressionAlternatives(exps(expressions));
}
