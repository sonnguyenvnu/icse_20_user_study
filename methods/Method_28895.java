public T get(){
  if (dependency != null && dependency.set && !dependency.built) {
    dependency.build();
  }
  if (!set) {
    throw new JedisDataException("Please close pipeline or multi block before calling this method.");
  }
  if (!built) {
    build();
  }
  if (exception != null) {
    throw exception;
  }
  return response;
}
