/** 
 * Find the root assignable expression of a chain of field accesses. If there is no root (i.e, a bare method call or a static method call), return null. <p>Examples: <pre> {@code a.trim().intern() ==> a a.b.trim().intern() ==> a.b this.intValue.foo() ==> this.intValue this.foo() ==> this intern() ==> null String.format() ==> null java.lang.String.format() ==> null}</pre>
 */
@Nullable public static ExpressionTree getRootAssignable(MethodInvocationTree methodInvocationTree){
  if (!(methodInvocationTree instanceof JCMethodInvocation)) {
    throw new IllegalArgumentException("Expected type to be JCMethodInvocation, but was " + methodInvocationTree.getClass());
  }
  if (((JCMethodInvocation)methodInvocationTree).getMethodSelect() instanceof JCIdent) {
    return null;
  }
  ExpressionTree expr=methodInvocationTree;
  while (expr instanceof JCMethodInvocation) {
    expr=((JCMethodInvocation)expr).getMethodSelect();
    if (expr instanceof JCFieldAccess) {
      expr=((JCFieldAccess)expr).getExpression();
    }
  }
  Symbol sym=getSymbol(expr);
  if (sym instanceof VarSymbol) {
    return expr;
  }
  return null;
}
