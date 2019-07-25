public boolean contains(SModuleReference mRef){
synchronized (LOCK) {
    return myDepGraph.contains(mRef);
  }
}
