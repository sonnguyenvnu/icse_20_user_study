public void clear(){
  for (  Entry<K,T> entry : resolved) {
    registry.remove(entry.key);
  }
  resolved.clear();
  Iterator<Entry<K,T>> it=registry.values().iterator();
  while (it.hasNext()) {
    it.remove();
  }
}
