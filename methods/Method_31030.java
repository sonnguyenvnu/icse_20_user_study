private static String makeUrl(String url,String[] prefixes){
  if (ArrayUtils.isEmpty(prefixes)) {
    return url;
  }
  boolean hasPrefix=false;
  for (  String prefix : prefixes) {
    if (url.regionMatches(true,0,prefix,0,prefix.length())) {
      hasPrefix=true;
      if (!url.regionMatches(false,0,prefix,0,prefix.length())) {
        url=prefix + url.substring(prefix.length());
      }
      break;
    }
  }
  if (!hasPrefix) {
    url=prefixes[0] + url;
  }
  return url;
}
