@Override public void remove(final Collection<String> keys){
  cache.invalidateAll(keys);
}
