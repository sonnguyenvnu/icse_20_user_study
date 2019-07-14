static List<String> getCompatibleVersions(Properties props,String key){
  ImmutableList.Builder<String> b=ImmutableList.builder();
  b.addAll(Stream.of(props.getProperty(key,"").split(",")).map(String::trim).filter(s -> !s.isEmpty()).collect(Collectors.toList()));
  return b.build();
}
