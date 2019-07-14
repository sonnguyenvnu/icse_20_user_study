@Override public Pattern getPathPattern(){
  if (externalPathPattern == null) {
    return Pattern.compile("WEB-INF\\/classes\\/(?!META-INF)..*");
  }
 else {
    return externalPathPattern;
  }
}
