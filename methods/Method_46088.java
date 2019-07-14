private List<NewService> buildNewServices(ProviderConfig<?> config){
  List<ServerConfig> servers=config.getServer();
  if (CommonUtils.isEmpty(servers)) {
    return Collections.emptyList();
  }
  return servers.stream().map(server -> {
    NewService service=new NewService();
    service.setId(buildServiceId(config,server));
    service.setName(buildServiceName(config));
    String host=getServerHost(server);
    int port=server.getPort();
    service.setAddress(host);
    service.setPort(port);
    Map<String,String> metaData=RegistryUtils.convertProviderToMap(config,server).entrySet().stream().filter(e -> ConsulUtils.isValidMetaKey(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    service.setMeta(metaData);
    service.setTags(Collections.singletonList(buildUniqueName(config,server.getProtocol())));
    service.setCheck(buildCheck(host,port));
    return service;
  }
).collect(Collectors.toList());
}
