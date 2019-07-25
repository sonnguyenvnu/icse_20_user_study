@Override public double metric(String name,long bucketOrd){
  DoubleHistogram state=getState(bucketOrd);
  if (state == null) {
    return Double.NaN;
  }
 else {
    return state.getValueAtPercentile(Double.parseDouble(name));
  }
}
