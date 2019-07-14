public Collection<R> getAllShards(){
  return Collections.unmodifiableCollection(resources.values());
}
