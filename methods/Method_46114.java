@Override public boolean needToLoad(ConsumerBootstrap consumerBootstrap){
  ConsumerConfig consumerConfig=consumerBootstrap.getConsumerConfig();
  final boolean isDirect=StringUtils.isNotBlank(consumerConfig.getDirectUrl());
  final List<RegistryConfig> registrys=consumerConfig.getRegistry();
  boolean isMesh=false;
  if (registrys != null) {
    for (    RegistryConfig registry : registrys) {
      if (registry.getProtocol().equalsIgnoreCase(RpcConstants.REGISTRY_PROTOCOL_MESH)) {
        isMesh=true;
        break;
      }
    }
  }
  boolean isBolt=consumerConfig.getProtocol().equalsIgnoreCase(RpcConstants.PROTOCOL_TYPE_BOLT);
  return !isDirect && isMesh && isBolt;
}
