public void unregister(Entry<K,T> entry){
  if (entry.isResolved()) {
    unresolve(entry);
  }
}
