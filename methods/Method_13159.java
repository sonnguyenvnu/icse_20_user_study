@Override public Pattern getPathPattern(){
  return (externalPathPattern != null) ? externalPathPattern : Pattern.compile("META-INF\\/services\\/[^\\/]+");
}
