static <T>ValueParser<T> enumerationParser(final Map<String,T> mappings){
  if (mappings.containsValue(null)) {
    throw new IllegalArgumentException("Map may not contain entries with null values");
  }
  return new ValueParser<T>(){
    @Override public T valueOf(    String value) throws IllegalArgumentException {
      if (!mappings.containsKey(value)) {
        throw new IllegalArgumentException("Value was not in the set " + mappings.keySet());
      }
      return mappings.get(value);
    }
  }
;
}
