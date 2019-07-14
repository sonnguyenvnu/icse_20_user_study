/** 
 * Checks whether the given expression is a equality/relation expression that compares with a size() call.
 * @param data the rule context
 * @param location the node location to report
 * @param expr the ==, &lt;, &gt; expression
 */
protected void checkNodeAndReport(Object data,Node location,Node expr){
  if ((expr instanceof ASTEqualityExpression || expr instanceof ASTRelationalExpression && getComparisonTargets().containsKey(expr.getImage())) && isCompare(expr)) {
    addViolation(data,location);
  }
}
