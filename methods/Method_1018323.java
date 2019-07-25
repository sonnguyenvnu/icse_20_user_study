@Override public Iterator<ResourceMetadata> iterator(){
  Set<ResourceMetadata> metadata=new HashSet<ResourceMetadata>();
  for (  ResourceMetadata candidate : cache.values()) {
    if (candidate != null) {
      metadata.add(candidate);
    }
  }
  return metadata.iterator();
}
