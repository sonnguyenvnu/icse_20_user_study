/** 
 * Wrap the provided aggregator so that it behaves (almost) as if it had been collected directly.
 */
@Override public Aggregator wrap(final Aggregator in){
  return new WrappedAggregator(in){
    @Override public InternalAggregation buildAggregation(    long bucket) throws IOException {
      if (selectedBuckets == null) {
        throw new IllegalStateException("Collection has not been replayed yet.");
      }
      final long rebasedBucket=selectedBuckets.find(bucket);
      if (rebasedBucket == -1) {
        throw new IllegalStateException("Cannot build for a bucket which has not been collected");
      }
      return in.buildAggregation(rebasedBucket);
    }
  }
;
}
