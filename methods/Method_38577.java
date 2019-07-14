public static String findMessage(final HttpServletRequest request,final Locale locale,final String key){
  String bundleName=(String)request.getAttribute(REQUEST_BUNDLE_NAME_ATTR);
  return MESSAGE_RESOLVER.findMessage(bundleName,locale,key);
}
