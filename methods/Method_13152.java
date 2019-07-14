@Override public Pattern getPathPattern(){
  if (externalPathPattern == null) {
    return Pattern.compile("^((?!module-info\\.class).)*$");
  }
 else {
    return externalPathPattern;
  }
}
