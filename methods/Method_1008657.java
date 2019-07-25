@Override public double percentile(double percent) throws IllegalArgumentException {
  Double value=percentiles.get(percent);
  if (value == null) {
    throw new IllegalArgumentException("Percent requested [" + String.valueOf(percent) + "] was not" + " one of the computed percentiles. Available keys are: " + percentiles.keySet());
  }
  return value;
}
