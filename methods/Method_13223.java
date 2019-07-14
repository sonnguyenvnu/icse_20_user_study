@Override public Pattern getPathPattern(){
  if (externalPathPattern == null) {
    return Pattern.compile("(META-INF\\/versions\\/.*)|(?!META-INF)..*");
  }
 else {
    return externalPathPattern;
  }
}
