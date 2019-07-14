public static String getForwardPathInfo(final HttpServletRequest request){
  return (String)request.getAttribute(FORWARD_PATH_INFO);
}
