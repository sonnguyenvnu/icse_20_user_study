public static Map<String,QueryParameter> splitQuery(URI uri){
  if (uri == null) {
    return Collections.emptyMap();
  }
  return splitQuery(uri.getRawQuery());
}
