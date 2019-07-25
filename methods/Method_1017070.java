public Summary summarize(){
  return new Summary(range,ShardedResultGroup.summarize(result),statistics,errors,trace,limits,preAggregationSampleSize,cache);
}
