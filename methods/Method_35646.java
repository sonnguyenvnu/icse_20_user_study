public static RequestPatternBuilder anyRequestedFor(UrlPattern urlPattern){
  return new RequestPatternBuilder(RequestMethod.ANY,urlPattern);
}
