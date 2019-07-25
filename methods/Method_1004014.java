@Override public Number add(Number sample){
  if (value == 0.0 || sample.doubleValue() < value) {
    value=sample.doubleValue();
  }
  return value;
}
