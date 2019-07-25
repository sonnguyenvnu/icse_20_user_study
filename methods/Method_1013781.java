@Override public String output(){
  return String.format("%s %s %s",ProxyContentParser.ContentType.COMPRESS.name(),algorithm.getType().name(),algorithm.version());
}
