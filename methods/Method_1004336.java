@NotNull public ResponseFieldMapper create(@NotNull Operation operation){
  checkNotNull(operation,"operation == null");
  Class operationClass=operation.getClass();
  ResponseFieldMapper mapper=pool.get(operationClass);
  if (mapper != null) {
    return mapper;
  }
  pool.putIfAbsent(operationClass,operation.responseFieldMapper());
  return pool.get(operationClass);
}
