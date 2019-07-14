@Override public Optional<Double> asDouble(){
  return getNativeValue().map(StringUtils::toDouble);
}
