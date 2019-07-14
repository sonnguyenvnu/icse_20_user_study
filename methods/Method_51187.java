/** 
 * Computes a metric identified by its code on a class AST node, possibly selecting a variant with the  {@code MetricOptions} parameter.
 * @param key     The key identifying the metric to be computed
 * @param node    The node on which to compute the metric
 * @param options The options of the metric
 * @return The value of the metric, or {@code Double.NaN} if the value couldn't be computed
 */
public double computeForType(MetricKey<T> key,T node,MetricOptions options){
  Objects.requireNonNull(key,NULL_KEY_MESSAGE);
  Objects.requireNonNull(options,NULL_OPTIONS_MESSAGE);
  Objects.requireNonNull(node,NULL_NODE_MESSAGE);
  if (!key.supports(node)) {
    return Double.NaN;
  }
  MetricMemoizer<T> memoizer=getLanguageSpecificProjectMemoizer().getClassMemoizer(node.getQualifiedName());
  return memoizer == null ? Double.NaN : getLanguageSpecificComputer().computeForType(key,node,false,options,memoizer);
}
