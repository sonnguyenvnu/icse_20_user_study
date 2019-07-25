protected void activate(String alias,HttpContext httpContext){
  try {
    logger.debug("Starting up {} at {}",getClass().getSimpleName(),alias);
    Hashtable<String,String> props=new Hashtable<String,String>();
    httpService.registerServlet(alias,this,props,httpContext);
  }
 catch (  NamespaceException e) {
    logger.error("Error during servlet registration - alias {} already in use",alias,e);
  }
catch (  ServletException e) {
    logger.error("Error during servlet registration",e);
  }
}
