private void copyMethods(ProviderConfig<T> providerConfig,ServiceConfig<T> serviceConfig){
  Map<String,MethodConfig> methodConfigs=providerConfig.getMethods();
  if (CommonUtils.isNotEmpty(methodConfigs)) {
    List<com.alibaba.dubbo.config.MethodConfig> dubboMethodConfigs=new ArrayList<com.alibaba.dubbo.config.MethodConfig>();
    for (    Map.Entry<String,MethodConfig> entry : methodConfigs.entrySet()) {
      MethodConfig methodConfig=entry.getValue();
      com.alibaba.dubbo.config.MethodConfig dubboMethodConfig=new com.alibaba.dubbo.config.MethodConfig();
      dubboMethodConfig.setName(methodConfig.getName());
      dubboMethodConfig.setParameters(methodConfig.getParameters());
      dubboMethodConfigs.add(dubboMethodConfig);
    }
    serviceConfig.setMethods(dubboMethodConfigs);
  }
}
