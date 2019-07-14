public static List<String> buildServiceIds(ProviderConfig<?> config){
  List<ServerConfig> servers=config.getServer();
  if (CommonUtils.isEmpty(servers)) {
    return Collections.emptyList();
  }
  return servers.stream().map(server -> buildServiceId(config,server)).collect(Collectors.toList());
}
