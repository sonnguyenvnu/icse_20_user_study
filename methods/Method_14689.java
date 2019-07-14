public static String getDomain(HttpServletRequest request){
  String host=request.getHeader("Host");
  if (host == null) {
    return DOMAIN;
  }
  int index=host.indexOf(':');
  return (index > -1) ? host.substring(0,index) : host;
}
