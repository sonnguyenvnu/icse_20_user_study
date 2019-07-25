@Override public double metric(String name,long bucketOrd){
  TDigestState state=getState(bucketOrd);
  if (state == null) {
    return Double.NaN;
  }
 else {
    return InternalTDigestPercentileRanks.percentileRank(state,Double.valueOf(name));
  }
}
