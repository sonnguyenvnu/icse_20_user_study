@Override public Optional<Integer> asInt(){
  return getNativeValue().map(StringUtils::toInt);
}
