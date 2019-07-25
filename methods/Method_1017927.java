public Object inspect(String path,String inspectionsPath,String isDevMode,String projectVersion){
  AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext();
  Configuration config=new DefaultConfiguration(path,inspectionsPath,isDevMode,projectVersion);
  PropertySourcesPlaceholderConfigurer ppc=new PropertySourcesPlaceholderConfigurer();
  ppc.setLocation(new FileSystemResource(config.getServicesConfigLocation()));
  ppc.setIgnoreUnresolvablePlaceholders(true);
  ppc.setIgnoreResourceNotFound(false);
  ppc.postProcessBeanFactory(ctx.getBeanFactory());
  ppc.setEnvironment(ctx.getEnvironment());
  PropertySources sources=ppc.getAppliedPropertySources();
  sources.forEach(source -> ctx.getEnvironment().getPropertySources().addLast(source));
  ctx.scan("com.thinkbiganalytics.install.inspector.inspection","com.thinkbiganalytics.hive.config","com.thinkbiganalytics.spring");
  ctx.refresh();
  InspectionService service=ctx.getBean(InspectionService.class);
  return service.inspect(config);
}
