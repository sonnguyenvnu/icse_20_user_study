private static SofaModuleProfileEnvironment createSofaProfileEnvironment(ApplicationContext applicationContext){
  SofaModuleProfileEnvironment environment=map.get(applicationContext);
  if (environment == null) {
    environment=new DefaultSofaModuleProfileEnvironment();
    environment.initEnvironment(applicationContext);
    SofaModuleProfileEnvironment oldEnvironment=map.putIfAbsent(applicationContext,environment);
    if (oldEnvironment != null) {
      environment=oldEnvironment;
    }
  }
  return environment;
}
