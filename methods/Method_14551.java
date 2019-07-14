public static void deleteCredentials(HttpServletRequest request,HttpServletResponse response,Provider provider,Type type){
  CookiesUtilities.deleteCookie(request,response,type.getCookieName(provider));
}
