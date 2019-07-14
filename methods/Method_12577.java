@Bean public SpringResourceTemplateResolver adminTemplateResolver(){
  SpringResourceTemplateResolver resolver=new SpringResourceTemplateResolver();
  resolver.setApplicationContext(this.applicationContext);
  resolver.setPrefix(this.adminUi.getTemplateLocation());
  resolver.setSuffix(".html");
  resolver.setTemplateMode(TemplateMode.HTML);
  resolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
  resolver.setCacheable(this.adminUi.isCacheTemplates());
  resolver.setOrder(10);
  resolver.setCheckExistence(true);
  return resolver;
}
