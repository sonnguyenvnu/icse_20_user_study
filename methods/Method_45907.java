public static void copyRegistries(com.alipay.sofa.rpc.config.AbstractInterfaceConfig sofaConfig,com.alibaba.dubbo.config.AbstractInterfaceConfig dubboConfig){
  List<RegistryConfig> registryConfigs=sofaConfig.getRegistry();
  if (CommonUtils.isNotEmpty(registryConfigs)) {
    List<com.alibaba.dubbo.config.RegistryConfig> dubboRegistryConfigs=new ArrayList<com.alibaba.dubbo.config.RegistryConfig>();
    for (    RegistryConfig registryConfig : registryConfigs) {
      com.alibaba.dubbo.config.RegistryConfig dubboRegistryConfig=DubboSingleton.REGISTRY_MAP.get(registryConfig);
      if (dubboRegistryConfig == null) {
        dubboRegistryConfig=new com.alibaba.dubbo.config.RegistryConfig();
        copyRegistryFields(registryConfig,dubboRegistryConfig);
        com.alibaba.dubbo.config.RegistryConfig old=DubboSingleton.REGISTRY_MAP.putIfAbsent(registryConfig,dubboRegistryConfig);
        if (old != null) {
          dubboRegistryConfig=old;
        }
      }
      dubboRegistryConfigs.add(dubboRegistryConfig);
    }
    dubboConfig.setRegistries(dubboRegistryConfigs);
  }
}
