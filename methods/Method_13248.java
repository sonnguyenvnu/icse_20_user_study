protected Container.Entry getEntry(String internalPath){
  String path=internalPath + ".class";
  if (entry.getPath().equals(path)) {
    return entry;
  }
  for (  Container.Entry e : entry.getParent().getChildren()) {
    if (e.getPath().equals(path)) {
      return e;
    }
  }
  return null;
}
