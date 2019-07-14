private static ImmutableMap<String,String> doExtractCookies(final String[] cookieStrings){
  ImmutableMap.Builder<String,String> builder=ImmutableMap.builder();
  for (  String cookie : cookieStrings) {
    Set<Cookie> decodeCookies=ServerCookieDecoder.STRICT.decode(cookie);
    for (    Cookie decodeCookie : decodeCookies) {
      builder.put(decodeCookie.name(),decodeCookie.value());
    }
  }
  return builder.build();
}
