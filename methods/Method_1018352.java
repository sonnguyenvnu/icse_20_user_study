private void configure(){
  setContextClass(AnnotationConfigWebApplicationContext.class);
  setContextConfigLocation(RepositoryRestMvcConfiguration.class.getName());
}
