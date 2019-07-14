/** 
 * Computes a metric identified by its code on a class AST node, possibly selecting metric options with the  {@code options} parameter.
 * @param key     The key identifying the metric to be computed
 * @param node    The node on which to compute the metric
 * @param options The options of the metric
 * @return The value of the metric, or {@code Double.NaN} if the value couldn't be computed
 */
public static double get(MetricKey<ASTUserClassOrInterface<?>> key,ASTUserClass node,MetricOptions options){
  return FACADE.computeForType(key,node,options);
}
