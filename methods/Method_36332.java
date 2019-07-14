public void resolve(Entry<K,T> entry){
  resolved.add(entry);
  Set<Entry<K,T>> dependencies=entry.getDependsOnMe();
  if (dependencies != null) {
    for (    Entry<K,T> dep : dependencies) {
      dep.removeWaitingFor(entry);
      if (dep.isResolved()) {
        resolve(dep);
      }
    }
  }
}
