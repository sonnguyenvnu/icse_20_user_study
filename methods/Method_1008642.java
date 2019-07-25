@Override public double metric(String name,long bucketOrd){
  TDigestState state=getState(bucketOrd);
  if (state == null) {
    return Double.NaN;
  }
 else {
    return state.quantile(Double.parseDouble(name) / 100);
  }
}
