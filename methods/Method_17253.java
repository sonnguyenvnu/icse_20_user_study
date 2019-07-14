@Override public void clear(){
  if (!hasRemovalListener() && (writer == CacheWriter.disabledWriter())) {
    data.clear();
    return;
  }
  for (  K key : data.keySet()) {
    remove(key);
  }
}
