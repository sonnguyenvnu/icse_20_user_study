@Override public void handleEvent(ContextRefreshedEvent event){
  ApplicationContext applicationContext=event.getApplicationContext();
  AliCloudProperties aliCloudProperties=applicationContext.getBean(AliCloudProperties.class);
  EdasProperties edasProperties=applicationContext.getBean(EdasProperties.class);
  AnsProperties ansProperties=applicationContext.getBean(AnsProperties.class);
  AliCloudEdasSdk aliCloudEdasSdk=applicationContext.getBean(AliCloudEdasSdk.class);
  AliCloudAnsInitializer.initialize(aliCloudProperties,edasProperties,ansProperties,aliCloudEdasSdk);
}
