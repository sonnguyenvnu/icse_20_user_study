private void visit(FileSystemListener listener,Set<FileSystemListener> result){
  if (result.contains(listener)) {
    return;
  }
  result.add(listener);
  Iterable<FileSystemListener> dependencies=listener.getListenerDependencies();
  if (dependencies == null) {
    return;
  }
  boolean readd=false;
  for (  FileSystemListener dep : dependencies) {
    if (myListener2Data.containsKey(dep) && !(result.contains(dep))) {
      visit(dep,result);
      readd=true;
    }
  }
  if (readd) {
    result.remove(listener);
    result.add(listener);
  }
}
