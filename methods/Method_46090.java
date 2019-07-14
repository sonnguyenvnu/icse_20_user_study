public static String buildServiceName(AbstractInterfaceConfig config){
  String consulServiceName=config.getParameter(ConsulConstants.CONSUL_SERVICE_NAME_KEY);
  if (consulServiceName != null) {
    return consulServiceName;
  }
  return config.getInterfaceId();
}
