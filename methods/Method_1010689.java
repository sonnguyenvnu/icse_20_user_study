@Override boolean exists(String path){
  ensureInitialized();
  return (myEntries.get(path) != null) || (mySubDirectories.get(path) != null);
}
