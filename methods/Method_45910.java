private void copyApplication(ProviderConfig<T> providerConfig,ServiceConfig<T> serviceConfig){
  ApplicationConfig applicationConfig=providerConfig.getApplication();
  com.alibaba.dubbo.config.ApplicationConfig dubboConfig=new com.alibaba.dubbo.config.ApplicationConfig();
  dubboConfig.setName(applicationConfig.getAppName());
  serviceConfig.setApplication(dubboConfig);
}
