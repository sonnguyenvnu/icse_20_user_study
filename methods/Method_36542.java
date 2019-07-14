@Override public Class<?> getTargetClass(){
  return applicationContext.getBean(this.beanName).getClass();
}
