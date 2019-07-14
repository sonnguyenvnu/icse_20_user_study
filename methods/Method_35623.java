public static UrlPathPattern urlPathMatching(String urlRegex){
  return new UrlPathPattern(matching(urlRegex),true);
}
