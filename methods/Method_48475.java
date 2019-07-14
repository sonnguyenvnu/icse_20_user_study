static final Set<String> getPropertySet(Properties props,String key){
  ImmutableSet.Builder<String> buildSet=ImmutableSet.builder();
  buildSet.addAll(Stream.of(props.getProperty(key,"").split(",")).map(String::trim).filter(s -> !s.isEmpty()).collect(Collectors.toList()));
  return buildSet.build();
}
