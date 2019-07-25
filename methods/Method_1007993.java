/** 
 * Utility method to build request's endpoint.
 */
static String endpoint(String... parts){
  StringJoiner joiner=new StringJoiner("/","/","");
  for (  String part : parts) {
    if (Strings.hasLength(part)) {
      try {
        URI uri=new URI(null,null,null,-1,"/" + part,null,null);
        joiner.add(uri.getRawPath().substring(1).replaceAll("/","%2F"));
      }
 catch (      URISyntaxException e) {
        throw new IllegalArgumentException("Path part [" + part + "] couldn't be encoded",e);
      }
    }
  }
  return joiner.toString();
}
