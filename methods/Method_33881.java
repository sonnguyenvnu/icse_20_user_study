@Override protected WebApplicationContext createServletApplicationContext(){
  AnnotationConfigWebApplicationContext context=new AnnotationConfigWebApplicationContext();
  context.scan(ClassUtils.getPackageName(getClass()));
  return context;
}
