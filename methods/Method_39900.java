public static String resolveUrl(final String url,final String context){
  if (isAbsoluteUrl(url)) {
    return url;
  }
  if (!context.startsWith(StringPool.SLASH) || !url.startsWith(StringPool.SLASH)) {
    throw new IllegalArgumentException("Values of both 'context' and 'url' must start with '/'.");
  }
  if (context.equals(StringPool.SLASH)) {
    return url;
  }
 else {
    return (context + url);
  }
}
