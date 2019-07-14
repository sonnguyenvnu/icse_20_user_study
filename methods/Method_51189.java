/** 
 * Compute the sum, average, or highest value of the operation metric on all operations of the class node. The type of operation is specified by the  {@link ResultOption} parameter.
 * @param key          The key identifying the metric to be computed
 * @param node         The node on which to compute the metric
 * @param resultOption The result option to use
 * @param options      The options of the metric
 * @return The value of the metric, or {@code Double.NaN} if the value couldn't be computed or {@code resultOption}is  {@code null}
 */
public double computeWithResultOption(MetricKey<O> key,T node,MetricOptions options,ResultOption resultOption){
  Objects.requireNonNull(key,NULL_KEY_MESSAGE);
  Objects.requireNonNull(options,NULL_OPTIONS_MESSAGE);
  Objects.requireNonNull(node,NULL_NODE_MESSAGE);
  Objects.requireNonNull(resultOption,"The result option must not be null");
  return getLanguageSpecificComputer().computeWithResultOption(key,node,false,options,resultOption,getLanguageSpecificProjectMemoizer());
}
