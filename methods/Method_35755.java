public static String urlToPathParts(URI uri){
  Iterable<String> uriPathNodes=Splitter.on("/").omitEmptyStrings().split(uri.getPath());
  int nodeCount=Iterables.size(uriPathNodes);
  return nodeCount > 0 ? Joiner.on("-").join(from(uriPathNodes)) : "";
}
