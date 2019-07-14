public static RequestPatternBuilder putRequestedFor(UrlPattern urlPattern){
  return new RequestPatternBuilder(RequestMethod.PUT,urlPattern);
}
