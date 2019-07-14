public static String resolveUrl(final String url,final HttpServletRequest request){
  if (isAbsoluteUrl(url)) {
    return url;
  }
  if (url.startsWith(StringPool.SLASH)) {
    return getContextPath(request) + url;
  }
 else {
    return url;
  }
}
