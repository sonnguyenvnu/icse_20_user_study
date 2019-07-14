public static String findMessage(final HttpServletRequest request,final String key){
  String bundleName=(String)request.getAttribute(REQUEST_BUNDLE_NAME_ATTR);
  Locale locale=(Locale)request.getSession().getAttribute(SESSION_LOCALE_ATTR);
  return MESSAGE_RESOLVER.findMessage(bundleName,locale,key);
}
