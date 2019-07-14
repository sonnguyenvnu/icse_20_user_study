public static void copyRegistryFields(com.alipay.sofa.rpc.config.RegistryConfig sofaRegistryConfig,com.alibaba.dubbo.config.RegistryConfig dubboRegistryConfig){
  dubboRegistryConfig.setAddress(sofaRegistryConfig.getAddress());
  dubboRegistryConfig.setProtocol(sofaRegistryConfig.getProtocol());
  dubboRegistryConfig.setRegister(sofaRegistryConfig.isRegister());
  dubboRegistryConfig.setSubscribe(sofaRegistryConfig.isSubscribe());
  dubboRegistryConfig.setAddress(sofaRegistryConfig.getAddress());
  dubboRegistryConfig.setTimeout(sofaRegistryConfig.getTimeout());
  dubboRegistryConfig.setId(sofaRegistryConfig.getId());
  dubboRegistryConfig.setParameters(sofaRegistryConfig.getParameters());
}
