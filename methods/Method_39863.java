public static String getForwardServletPath(final HttpServletRequest request){
  return (String)request.getAttribute(FORWARD_SERVLET_PATH);
}
