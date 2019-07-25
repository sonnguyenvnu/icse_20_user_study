public static String client(final ServletRequest request){
  String clientHost=request.getRemoteAddr();
  if (request instanceof HttpServletRequest) {
    String XRealIP=((HttpServletRequest)request).getHeader(X_Real_IP);
    if (XRealIP != null && XRealIP.length() > 0)     clientHost=XRealIP;
  }
  return clientHost;
}
