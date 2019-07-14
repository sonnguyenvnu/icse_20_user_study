private WebApp _start(final ServletContext servletContext){
  if (servletContext != null) {
    this.servletContext=servletContext;
    servletContext.setAttribute(MADVOC_ATTR,this);
  }
  webapp=createWebApplication();
  log=LoggerFactory.getLogger(Madvoc.class);
  webapp.bindServletContext(servletContext);
  if (paramsFiles != null) {
    final Props params=loadMadvocParams(paramsFiles);
    webapp.withParams(params);
  }
  resolveMadvocConfigClass();
  if (madvocConfiguratorClass != null) {
    webapp.registerComponent(madvocConfiguratorClass);
  }
  log.info("Madvoc starting...");
  webapp.start();
  return webapp;
}
