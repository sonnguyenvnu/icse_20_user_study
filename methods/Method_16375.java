@Override default Collection<Object> values(){
  return keySet().stream().map(this::getProperty).filter(Objects::nonNull).collect(Collectors.toList());
}
