public static String findMessage(final String bundleName,final HttpServletRequest request,final String key){
  Locale locale=(Locale)request.getSession().getAttribute(SESSION_LOCALE_ATTR);
  return MESSAGE_RESOLVER.findMessage(bundleName,locale,key);
}
