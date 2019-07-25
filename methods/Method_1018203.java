@Nonnull @Override public SparkJobStatus apply(@Nonnull final SparkJobResponse response){
  final String cacheId=cache.putResult(response.getResult());
  return new DefaultSparkJobStatus(cacheId,cache);
}
