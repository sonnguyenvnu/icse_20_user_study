private void copyApplication(ConsumerConfig<T> consumerConfig,ReferenceConfig<T> referenceConfig){
  ApplicationConfig applicationConfig=consumerConfig.getApplication();
  com.alibaba.dubbo.config.ApplicationConfig dubboConfig=new com.alibaba.dubbo.config.ApplicationConfig();
  dubboConfig.setName(applicationConfig.getAppName());
  referenceConfig.setApplication(dubboConfig);
}
