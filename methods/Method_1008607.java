/** 
 * Wrap the provided aggregator so that it behaves (almost) as if it had been collected directly.
 */
public Aggregator wrap(final Aggregator in){
  return new WrappedAggregator(in);
}
