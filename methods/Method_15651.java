@Override public Optional<Long> asLong(){
  return getNativeValue().map(StringUtils::toLong);
}
