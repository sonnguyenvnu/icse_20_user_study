@Override public Optional<DataType> remove(List<Object> list,String key){
  if (!"[]".equals(key)) {
    throw new TraversrException("AutoExpandArrayTraversal expects a '[]' key. Was: " + key);
  }
  return Optional.empty();
}
