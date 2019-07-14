/** 
 * Computes the standard version of the metric identified by the key on a operation AST node.
 * @param key  The key identifying the metric to be computed
 * @param node The node on which to compute the metric
 * @return The value of the metric, or {@code Double.NaN} if the value couldn't be computed
 */
public static double get(MetricKey<ASTMethod> key,ASTMethod node){
  return FACADE.computeForOperation(key,node,MetricOptions.emptyOptions());
}
