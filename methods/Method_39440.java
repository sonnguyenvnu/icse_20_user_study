public Integer getIntegerValue(final String key,final Integer defaultValue,final String... profiles){
  final String value=getValue(key,profiles);
  if (value == null) {
    return defaultValue;
  }
  return Integer.valueOf(value);
}
