@Override public boolean exists(){
  if (isRoot()) {
    return true;
  }
synchronized (MEMORY_FILES) {
    return MEMORY_FILES.get(name) != null;
  }
}
