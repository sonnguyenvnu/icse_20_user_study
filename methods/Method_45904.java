private void copyMethods(ConsumerConfig<T> consumerConfig,ReferenceConfig<T> referenceConfig){
  Map<String,MethodConfig> methodConfigs=consumerConfig.getMethods();
  if (CommonUtils.isNotEmpty(methodConfigs)) {
    List<com.alibaba.dubbo.config.MethodConfig> dubboMethodConfigs=new ArrayList<com.alibaba.dubbo.config.MethodConfig>();
    for (    Map.Entry<String,MethodConfig> entry : methodConfigs.entrySet()) {
      MethodConfig methodConfig=entry.getValue();
      com.alibaba.dubbo.config.MethodConfig dubboMethodConfig=new com.alibaba.dubbo.config.MethodConfig();
      dubboMethodConfig.setName(methodConfig.getName());
      dubboMethodConfig.setParameters(methodConfig.getParameters());
      dubboMethodConfig.setTimeout(methodConfig.getTimeout());
      dubboMethodConfig.setRetries(methodConfig.getRetries());
      String invokeType=methodConfig.getInvokeType();
      if (invokeType != null) {
        if (RpcConstants.INVOKER_TYPE_ONEWAY.equals(invokeType)) {
          dubboMethodConfig.setReturn(false);
        }
        if (RpcConstants.INVOKER_TYPE_CALLBACK.equals(invokeType) || RpcConstants.INVOKER_TYPE_FUTURE.equals(invokeType)) {
          dubboMethodConfig.setAsync(true);
        }
      }
      dubboMethodConfigs.add(dubboMethodConfig);
    }
    referenceConfig.setMethods(dubboMethodConfigs);
  }
}
