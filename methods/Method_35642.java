public static RequestPatternBuilder postRequestedFor(UrlPattern urlPattern){
  return new RequestPatternBuilder(RequestMethod.POST,urlPattern);
}
