/** 
 * Computes a metric identified by its key on a operation AST node.
 * @param key     The key identifying the metric to be computed
 * @param node    The node on which to compute the metric
 * @param options The options of the metric
 * @return The value of the metric, or {@code Double.NaN} if the value couldn't be computed
 */
public double computeForOperation(MetricKey<O> key,O node,MetricOptions options){
  Objects.requireNonNull(key,NULL_KEY_MESSAGE);
  Objects.requireNonNull(options,NULL_OPTIONS_MESSAGE);
  Objects.requireNonNull(node,NULL_NODE_MESSAGE);
  if (!key.supports(node)) {
    return Double.NaN;
  }
  MetricMemoizer<O> memoizer=getLanguageSpecificProjectMemoizer().getOperationMemoizer(node.getQualifiedName());
  return memoizer == null ? Double.NaN : getLanguageSpecificComputer().computeForOperation(key,node,false,options,memoizer);
}
