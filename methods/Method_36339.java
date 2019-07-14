protected void updateDependencies(Entry<K,T> entry,Collection<K> requires){
  if (requires != null) {
    for (    K req : requires) {
      Entry<K,T> reqEntry=registry.get(req);
      if (reqEntry != null) {
        if (reqEntry.isResolved()) {
          reqEntry.addDependsOnMe(entry);
          entry.addDependency(reqEntry);
          continue;
        }
      }
 else {
        reqEntry=new Entry<>(req,null);
        registry.put(req,reqEntry);
      }
      reqEntry.addDependsOnMe(entry);
      entry.addDependency(reqEntry);
      entry.addWaitingFor(reqEntry);
    }
  }
}
