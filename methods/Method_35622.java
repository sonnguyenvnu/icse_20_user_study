public static UrlPathPattern urlPathEqualTo(String testUrl){
  return new UrlPathPattern(equalTo(testUrl),false);
}
