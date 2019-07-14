/** 
 * Compute the sum, average, or highest value of the operation metric on all operations of the class node. The type of operation is specified by the  {@link ResultOption} parameter.
 * @param key          The key identifying the metric to be computed
 * @param node         The node on which to compute the metric
 * @param resultOption The result option to use
 * @param options      The version of the metric
 * @return The value of the metric, or {@code Double.NaN} if the value couldn't be computed
 */
public static double get(MetricKey<MethodLikeNode> key,ASTAnyTypeDeclaration node,MetricOptions options,ResultOption resultOption){
  return FACADE.computeWithResultOption(key,node,options,resultOption);
}
