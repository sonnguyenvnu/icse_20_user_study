public static UrlPattern urlMatching(String urlRegex){
  return new UrlPattern(matching(urlRegex),true);
}
