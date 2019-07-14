public static String findDefaultMessage(final HttpServletRequest request,final String key){
  Locale locale=(Locale)request.getSession().getAttribute(SESSION_LOCALE_ATTR);
  return MESSAGE_RESOLVER.findDefaultMessage(locale,key);
}
