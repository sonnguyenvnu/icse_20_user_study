@Override public double percentile(double percent){
  if (state.getTotalCount() == 0) {
    return Double.NaN;
  }
  return state.getValueAtPercentile(percent);
}
