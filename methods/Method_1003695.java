@Override public ServerConfigBuilder require(String pointer,TypeToken<?> type){
  TypeToken<?> previous=required.put(Objects.requireNonNull(pointer,"pointer cannot be null"),Objects.requireNonNull(type,"type cannot be null"));
  if (previous != null) {
    throw new IllegalArgumentException("Cannot require config of type '" + type + "' at '" + pointer + "' as '" + previous + " has already been registered for this path");
  }
  return this;
}
