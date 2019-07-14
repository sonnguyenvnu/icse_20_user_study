/** 
 * ????basePath
 * @param request
 * @return
 */
public static String getBasePath(HttpServletRequest request){
  StringBuffer basePath=new StringBuffer();
  String scheme=request.getScheme();
  String domain=request.getServerName();
  int port=request.getServerPort();
  basePath.append(scheme);
  basePath.append("://");
  basePath.append(domain);
  if ("http".equalsIgnoreCase(scheme) && 80 != port) {
    basePath.append(":").append(String.valueOf(port));
  }
 else   if ("https".equalsIgnoreCase(scheme) && port != 443) {
    basePath.append(":").append(String.valueOf(port));
  }
  return basePath.toString();
}
