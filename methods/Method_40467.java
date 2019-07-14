public boolean isLibFile(String file){
  if (file.startsWith("/")) {
    return true;
  }
  if (path != null) {
    for (    String p : path) {
      if (file.startsWith(p)) {
        return true;
      }
    }
  }
  return false;
}
