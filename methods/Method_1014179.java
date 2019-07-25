@Activate protected void activate(Map<String,Object> config){
  try {
    Servlet servlet=getImpl();
    logger.debug("Starting up '{}' servlet  at /{}",servlet.getServletInfo(),PROXY_ALIAS);
    Hashtable<String,String> props=propsFromConfig(config);
    httpService.registerServlet("/" + PROXY_ALIAS,servlet,props,createHttpContext());
  }
 catch (  NamespaceException|ServletException e) {
    logger.error("Error during servlet startup: {}",e.getMessage());
  }
}
