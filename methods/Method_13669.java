@Override public void onApplicationEvent(T event){
  if (event instanceof ApplicationContextEvent) {
    ApplicationContext applicationContext=((ApplicationContextEvent)event).getApplicationContext();
    if (BOOTSTRAP_CONFIG_NAME_VALUE.equals(applicationContext.getEnvironment().getProperty(BOOTSTRAP_CONFIG_NAME_KEY))) {
      return;
    }
  }
  Class<?> clazz=getClass();
  lockMap.putIfAbsent(clazz,new AtomicBoolean(false));
  AtomicBoolean handled=lockMap.get(clazz);
  if (!handled.compareAndSet(false,true)) {
    return;
  }
  if (conditionalOnClass() != null) {
    try {
      Class.forName(conditionalOnClass());
    }
 catch (    ClassNotFoundException e) {
      return;
    }
  }
  handleEvent(event);
}
