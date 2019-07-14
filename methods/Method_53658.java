/** 
 * ?????IP??
 * @param request ??
 * @return
 */
public static String getIpAddr(HttpServletRequest request){
  String ip=request.getHeader("x-forwarded-for");
  if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
    ip=request.getHeader("Proxy-Client-IP");
  }
  if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
    ip=request.getHeader("WL-Proxy-Client-IP");
  }
  if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
    ip=request.getRemoteAddr();
    if (ip.equals("127.0.0.1")) {
      InetAddress inet=null;
      try {
        inet=InetAddress.getLocalHost();
      }
 catch (      UnknownHostException e) {
        e.printStackTrace();
      }
      ip=inet.getHostAddress();
    }
  }
  if (ip != null && ip.length() > 15) {
    if (ip.indexOf(",") > 0) {
      ip=ip.substring(0,ip.indexOf(","));
    }
  }
  return ip;
}
