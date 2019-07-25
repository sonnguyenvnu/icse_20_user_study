private static ServiceSpecification generate(List<ServiceConfig> services,DocServiceFilter filter){
  return ServiceSpecification.merge(plugins.stream().map(plugin -> plugin.generateSpecification(findSupportedServices(plugin,services),filter)).collect(toImmutableList()));
}
