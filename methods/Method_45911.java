private void copyServers(ProviderConfig<T> providerConfig,ServiceConfig serviceConfig){
  List<ServerConfig> serverConfigs=providerConfig.getServer();
  if (CommonUtils.isNotEmpty(serverConfigs)) {
    List<ProtocolConfig> dubboProtocolConfigs=new ArrayList<ProtocolConfig>();
    for (    ServerConfig serverConfig : serverConfigs) {
      ProtocolConfig protocolConfig=DubboSingleton.SERVER_MAP.get(serverConfig);
      if (protocolConfig == null) {
        protocolConfig=new ProtocolConfig();
        copyServerFields(serverConfig,protocolConfig);
        ProtocolConfig old=DubboSingleton.SERVER_MAP.putIfAbsent(serverConfig,protocolConfig);
        if (old != null) {
          protocolConfig=old;
        }
      }
      dubboProtocolConfigs.add(protocolConfig);
    }
    serviceConfig.setProtocols(dubboProtocolConfigs);
  }
}
