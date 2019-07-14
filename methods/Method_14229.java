@SuppressWarnings("unchecked") static public Properties getRequestParameters(HttpServletRequest request){
  Properties options=new Properties();
  Enumeration<String> en=request.getParameterNames();
  while (en.hasMoreElements()) {
    String name=en.nextElement();
    options.put(name,request.getParameter(name));
  }
  return options;
}
