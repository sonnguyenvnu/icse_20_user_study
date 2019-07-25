/** 
 * Creates the pipeline aggregator
 * @return The created aggregator
 */
@Override public final PipelineAggregator create() throws IOException {
  PipelineAggregator aggregator=createInternal(this.metaData);
  return aggregator;
}
