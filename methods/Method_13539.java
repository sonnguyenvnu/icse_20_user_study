private String resolveServiceName(HttpServletRequest request){
  String requestURI=request.getRequestURI();
  String servletPath=request.getServletPath();
  String part=substringAfter(requestURI,servletPath);
  String serviceName=substringBetween(part,"/","/");
  return serviceName;
}
