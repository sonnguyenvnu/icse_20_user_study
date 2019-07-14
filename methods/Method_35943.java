@Override public FaultInjector buildFaultInjector(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
  if (httpServletRequest.getScheme().equals("https")) {
    return new JettyHttpsFaultInjector(httpServletResponse);
  }
  return new JettyFaultInjector(httpServletResponse);
}
