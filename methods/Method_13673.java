@Bean(initMethod="init") @ConditionalOnMissingBean public SchedulerXClient schedulerXClient(AliCloudProperties aliCloudProperties,EdasProperties edasProperties,ScxProperties scxProperties,AliCloudEdasSdk aliCloudEdasSdk){
  return AliCloudScxInitializer.initialize(aliCloudProperties,edasProperties,scxProperties,aliCloudEdasSdk);
}
