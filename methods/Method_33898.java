@Override protected WebApplicationContext createServletApplicationContext(){
  AnnotationConfigWebApplicationContext context=new AnnotationConfigWebApplicationContext();
  context.register(SecurityConfig.class,WebMvcConfig.class);
  return context;
}
