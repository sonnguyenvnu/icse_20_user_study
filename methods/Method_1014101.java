/** 
 * Adds a value to the resulting AST tree, if the given expression matches.
 * @param expression the expression that has to match
 * @param tag the tag that's to be set
 * @return resulting expression
 */
protected Expression tag(Object expression,Object tag){
  return tag(null,expression,tag);
}
