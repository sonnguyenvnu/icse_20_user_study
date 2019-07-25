/** 
 * Actual plugin initialization write plugin info and handle webappDir property. If the webappDir property is set, call: <pre> contextHandler.setBaseResource(new ResourceCollection( new FileResource(webappDir), contextHandler.getBaseResource() )); </pre>
 * @param contextHandler instance of ContextHandler - main jetty class for webapp.
 */
public void init(Object contextHandler){
  ClassLoader loader=contextHandler.getClass().getClassLoader();
  Class contextHandlerClass;
  Class resourceClass;
  Class fileResourceClass;
  Class resourceCollectionClass;
  try {
    contextHandlerClass=loader.loadClass("org.eclipse.jetty.server.handler.ContextHandler");
    resourceClass=loader.loadClass("org.eclipse.jetty.util.resource.Resource");
    fileResourceClass=loader.loadClass("org.eclipse.jetty.util.resource.FileResource");
    resourceCollectionClass=loader.loadClass("org.eclipse.jetty.util.resource.ResourceCollection");
  }
 catch (  ClassNotFoundException e) {
    try {
      contextHandlerClass=loader.loadClass("org.mortbay.jetty.handler.ContextHandler");
      resourceClass=loader.loadClass("org.mortbay.resource.Resource");
      fileResourceClass=loader.loadClass("org.mortbay.resource.FileResource");
      resourceCollectionClass=loader.loadClass("org.mortbay.resource.ResourceCollection");
    }
 catch (    ClassNotFoundException e1) {
      LOGGER.error("Unable to load ContextHandler class from contextHandler {} classloader",contextHandler);
      return;
    }
  }
  String version;
  try {
    Object server=ReflectionHelper.invoke(contextHandler,contextHandlerClass,"getServer",new Class[]{});
    version=server.getClass().getPackage().getImplementationVersion();
  }
 catch (  Exception e) {
    version="unknown [" + e.getMessage() + "]";
  }
  URL[] webappDir=pluginConfiguration.getWebappDir();
  if (webappDir.length > 0) {
    try {
      Object originalBaseResource=ReflectionHelper.invoke(contextHandler,contextHandlerClass,"getBaseResource",new Class[]{});
      Object resourceArray=Array.newInstance(resourceClass,webappDir.length + 1);
      for (int i=0; i < webappDir.length; i++) {
        LOGGER.debug("Watching 'webappDir' for changes: {} in Jetty webapp: {}",webappDir[i],contextHandler);
        Object fileResource=fileResourceClass.getDeclaredConstructor(URL.class).newInstance(webappDir[i]);
        Array.set(resourceArray,i,fileResource);
      }
      Array.set(resourceArray,webappDir.length,originalBaseResource);
      Object resourceCollection=resourceCollectionClass.getDeclaredConstructor(resourceArray.getClass()).newInstance(resourceArray);
      ReflectionHelper.invoke(contextHandler,contextHandlerClass,"setBaseResource",new Class[]{resourceClass},resourceCollection);
    }
 catch (    Exception e) {
      LOGGER.error("Unable to set webappDir to directory '{}' for Jetty webapp {}. This configuration will not work.",e,webappDir[0],contextHandler);
    }
  }
  LOGGER.info("Jetty plugin initialized - Jetty version '{}', context {}",version,contextHandler);
}
