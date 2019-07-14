/** 
 * Creates and starts new <code>Madvoc</code> web application. <code>Madvoc</code> instance is stored in servlet context. Important: <code>servletContext</code> may be <code>null</code>, when web application is run out from container.
 */
@SuppressWarnings("InstanceofCatchParameter") public WebApp startWebApplication(final ServletContext servletContext){
  try {
    WebApp webApp=_start(servletContext);
    log.info("Madvoc is up and running.");
    return webApp;
  }
 catch (  Exception ex) {
    if (log != null) {
      log.error("Madvoc startup failure.",ex);
    }
 else {
      ex.printStackTrace();
    }
    if (ex instanceof MadvocException) {
      throw (MadvocException)ex;
    }
    throw new MadvocException(ex);
  }
}
