/** 
 * ??ip??????getRemoteAddr???ip????
 * @param request
 * @return
 */
public static String getIpAddr(HttpServletRequest request){
  String ip=request.getHeader("Cdn-Src-Ip");
  if (ip == null || ip.length() == 0 || " unknown".equalsIgnoreCase(ip)) {
    ip=request.getHeader("HTTP_CLIENT_IP");
  }
  if (ip == null || ip.length() == 0 || " unknown".equalsIgnoreCase(ip)) {
    ip=request.getHeader("X-Forwarded-For");
  }
  if (ip == null || ip.length() == 0 || " unknown".equalsIgnoreCase(ip)) {
    ip=request.getHeader("Proxy-Client-IP");
  }
  if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
    ip=request.getHeader("WL-Proxy-Client-IP");
  }
  if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
    ip=request.getRemoteAddr();
  }
  return ip;
}
