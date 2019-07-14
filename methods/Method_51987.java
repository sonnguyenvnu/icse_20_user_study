/** 
 * Computes a metric identified by its key on a operation AST node.
 * @param key     The key identifying the metric to be computed
 * @param node    The node on which to compute the metric
 * @param options The options of the metric
 * @return The value of the metric, or {@code Double.NaN} if the value couldn't be computed
 */
public static double get(MetricKey<MethodLikeNode> key,MethodLikeNode node,MetricOptions options){
  return FACADE.computeForOperation(key,node,options);
}
