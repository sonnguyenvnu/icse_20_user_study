public static RequestPatternBuilder getRequestedFor(UrlPattern urlPattern){
  return new RequestPatternBuilder(RequestMethod.GET,urlPattern);
}
