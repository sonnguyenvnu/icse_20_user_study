/** 
 * Runs the  {@code transfer} dataflow analysis to compute the abstract value of the expressionwhich is the leaf of  {@code exprPath}. <p>The expression must be part of a method, lambda, or initializer (inline field initializer or initializer block). Example of an expression outside of such constructs is the identifier in an import statement. <p>Note that for intializers, each inline field initializer or initializer block is treated separately. I.e., we don't merge all initializers into one virtual block for dataflow.
 * @return dataflow result for the given expression or {@code null} if the expression is not partof a method, lambda or initializer
 */
@Nullable public static <A extends AbstractValue<A>,S extends Store<S>,T extends TransferFunction<A,S>>A expressionDataflow(TreePath exprPath,Context context,T transfer){
  final Tree leaf=exprPath.getLeaf();
  Preconditions.checkArgument(leaf instanceof ExpressionTree,"Leaf of exprPath must be of type ExpressionTree, but was %s",leaf.getClass().getName());
  final ExpressionTree expr=(ExpressionTree)leaf;
  final TreePath enclosingMethodPath=findEnclosingMethodOrLambdaOrInitializer(exprPath);
  if (enclosingMethodPath == null) {
    return null;
  }
  final Tree method=enclosingMethodPath.getLeaf();
  if (method instanceof MethodTree && ((MethodTree)method).getBody() == null) {
    return null;
  }
  return methodDataflow(enclosingMethodPath,context,transfer).getAnalysis().getValue(expr);
}
