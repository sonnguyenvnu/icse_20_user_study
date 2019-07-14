private static HystrixCommandGroupKey initGroupKey(final HystrixCommandGroupKey fromConstructor){
  if (fromConstructor == null) {
    throw new IllegalStateException("HystrixCommandGroup can not be NULL");
  }
 else {
    return fromConstructor;
  }
}
