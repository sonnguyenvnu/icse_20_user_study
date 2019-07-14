public static String makeSafeFileName(StubMapping mapping,String extension){
  String suffix="-" + mapping.getId() + "." + extension;
  if (isNotEmpty(mapping.getName())) {
    return makeSafeName(mapping.getName()) + suffix;
  }
  UrlPattern urlMatcher=mapping.getRequest().getUrlMatcher();
  if (urlMatcher.getPattern() instanceof AnythingPattern) {
    return suffix.substring(1);
  }
  String expectedUrl=urlMatcher.getExpected();
  URI uri=URI.create(sanitise(expectedUrl));
  return makeSafeNameFromUrl(uri.getPath()) + suffix;
}
