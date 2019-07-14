/** 
 * Checks if the equality expression passed in is of comparing against the value passed in as i
 * @param equality
 * @param i The ordinal in the equality expression to check
 * @return true if the value in position i is one of the comparison targets,else false
 * @see #getComparisonTargets()
 */
private boolean checkComparison(String operator,Node equality,int i){
  Node target=equality.jjtGetChild(i).jjtGetChild(0).jjtGetChild(0);
  return target instanceof ASTLiteral && getComparisonTargets().get(operator).contains(target.getImage());
}
