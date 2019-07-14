@Bean(initMethod="start",destroyMethod="destroy") public SpringLifeCycle create(){
  SpringLifeCycle springLifeCycle=new SpringLifeCycle();
  return springLifeCycle;
}
