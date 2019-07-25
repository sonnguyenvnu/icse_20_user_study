public Optional<ClusterNode> resolve(final String bindName){
  return Optional.ofNullable(binds.get(bindName));
}
