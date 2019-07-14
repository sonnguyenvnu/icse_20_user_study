@Override public Registry getRegistry(URL url){
  init();
  return new SpringCloudRegistry(url,discoveryClient,dubboServiceMetadataRepository,dubboMetadataConfigServiceProxy,jsonUtils,servicesLookupScheduler);
}
