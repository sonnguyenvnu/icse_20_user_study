public void unresolve(Entry<K,T> entry){
  Set<Entry<K,T>> dependencies=entry.getDependsOnMe();
  if (dependencies != null) {
    for (    Entry<K,T> dep : dependencies) {
      dep.addWaitingFor(entry);
      if (!dep.isResolved()) {
        unresolve(dep);
      }
    }
  }
  resolved.remove(entry);
}
