public Collection<S> getAllShardInfo(){
  return Collections.unmodifiableCollection(nodes.values());
}
