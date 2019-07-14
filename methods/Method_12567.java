protected String getManagementPort(ServiceInstance instance){
  String managementPort=instance.getMetadata().get(KEY_MANAGEMENT_PORT);
  if (!isEmpty(managementPort)) {
    return managementPort;
  }
  return String.valueOf(getServiceUrl(instance).getPort());
}
