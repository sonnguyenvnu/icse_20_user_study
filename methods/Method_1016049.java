@Override protected void initiate(ResourceConfig config,WebApplication webapp){
  this.webapp=webapp;
  GovernatorComponentProviderFactory factory=new GovernatorComponentProviderFactory(config,injector);
  webapp.initiate(config,factory);
  for (  Class<?> resource : config.getRootResourceClasses()) {
    if (resource.isAnnotationPresent(com.google.inject.Singleton.class) || resource.isAnnotationPresent(javax.inject.Singleton.class)) {
      LOG.warn("Class {} should be annotated with Jersey's com.sun.jersey.spi.resource.Singleton.  Also make sure that any JAX-RS clasese (such as UriInfo) are injected using Jersey's @Context instead of @Inject.",resource);
    }
  }
}
