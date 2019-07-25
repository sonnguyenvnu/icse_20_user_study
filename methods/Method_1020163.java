@Bean @RequestScope public MyApplet applet(Injector injector){
  return injector.getInstance(MyApplet.class);
}
