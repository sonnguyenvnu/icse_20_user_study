@Override public double value(String name){
  if ("sum_of_squares".equals(name)) {
    return sumOfSqrs;
  }
  if ("variance".equals(name)) {
    return getVariance();
  }
  if ("std_deviation".equals(name)) {
    return getStdDeviation();
  }
  if ("std_upper".equals(name)) {
    return getStdDeviationBound(Bounds.UPPER);
  }
  if ("std_lower".equals(name)) {
    return getStdDeviationBound(Bounds.LOWER);
  }
  return super.value(name);
}
