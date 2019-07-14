@Override public Pattern getPathPattern(){
  if (externalPathPattern == null) {
    return Pattern.compile("(.*\\/)?META-INF\\/services\\/.*");
  }
 else {
    return externalPathPattern;
  }
}
