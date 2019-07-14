public static void setCredentials(HttpServletRequest request,HttpServletResponse response,Credentials credentials,Type type,int max_age){
  String name=type.getCookieName(credentials.getProvider());
  String value=credentials.toString();
  CookiesUtilities.setCookie(request,response,name,value,max_age);
}
