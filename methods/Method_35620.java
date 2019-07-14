public static UrlPattern urlEqualTo(String testUrl){
  return new UrlPattern(equalTo(testUrl),false);
}
