@Override public String unmerge(String propertyName,UriProperties toDelete,Map<String,UriProperties> propertiesToMerge){
  if (toDelete.Uris().size() > 1) {
  }
  Set<URI> uris=toDelete.Uris();
  if (uris.size() == 0) {
    throw new IllegalArgumentException("The Uri to delete is not specified.");
  }
  URI uri=uris.iterator().next();
  for (  Map.Entry<String,UriProperties> property : propertiesToMerge.entrySet()) {
    if (property.getValue().Uris().contains(uri)) {
      return property.getKey();
    }
  }
  return null;
}
