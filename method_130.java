private static Server _XXXXX_(String serverName,Collection<ServerServiceDefinition> services){
  InProcessServerBuilder builder=InProcessServerBuilder.forName(serverName);
  services.forEach(service -> builder.addService(service));
  return builder.directExecutor().build();
}