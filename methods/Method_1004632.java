private boolean include(String name){
  if (basePackages != null) {
    for (    String p : basePackages) {
      if (name.startsWith(p)) {
        return true;
      }
    }
  }
  return false;
}
