public static RequestPatternBuilder optionsRequestedFor(UrlPattern urlPattern){
  return new RequestPatternBuilder(RequestMethod.OPTIONS,urlPattern);
}
