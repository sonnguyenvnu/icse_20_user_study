CachedValuesHistogram getLatest(){
  startCachingStreamValuesIfUnstarted();
  if (rollingDistribution.hasValue()) {
    return rollingDistribution.getValue();
  }
 else {
    return null;
  }
}
