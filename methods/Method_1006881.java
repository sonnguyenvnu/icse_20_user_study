private void run(){
  final ApplicationContext parent=new ClassPathXmlApplicationContext(parentContextPath);
  parent.getAutowireCapableBeanFactory().autowireBeanProperties(this,AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE,false);
  parent.getAutowireCapableBeanFactory().initializeBean(this,getClass().getSimpleName());
  this.parentContext=parent;
}
