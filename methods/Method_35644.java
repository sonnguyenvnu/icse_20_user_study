public static RequestPatternBuilder deleteRequestedFor(UrlPattern urlPattern){
  return new RequestPatternBuilder(RequestMethod.DELETE,urlPattern);
}
