@Override public final String discover(){
  for (  String discoveryDirectory : discoveryDirectories()) {
    if (find(discoveryDirectory)) {
      return discoveryDirectory;
    }
  }
  return null;
}
