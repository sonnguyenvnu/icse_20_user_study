private TreeCache findTreeCache(final String key){
  for (  Entry<String,TreeCache> entry : caches.entrySet()) {
    if (key.startsWith(entry.getKey())) {
      return entry.getValue();
    }
  }
  return null;
}
