@Override public UUID convert(Object value){
  Preconditions.checkNotNull(value);
  if (value instanceof String) {
    return UUID.fromString((String)value);
  }
  return null;
}
