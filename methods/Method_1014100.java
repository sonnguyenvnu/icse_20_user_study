/** 
 * Adds a name to the resulting AST tree, if the given expression matches.
 * @param name name to add
 * @param expression the expression that has to match
 * @return resulting expression
 */
protected Expression tag(String name,Object expression){
  return tag(name,expression,null);
}
