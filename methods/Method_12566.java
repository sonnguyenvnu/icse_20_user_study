protected String getManagementHost(ServiceInstance instance){
  String managementServerHost=instance.getMetadata().get(KEY_MANAGEMENT_ADDRESS);
  if (!isEmpty(managementServerHost)) {
    return managementServerHost;
  }
  return getServiceUrl(instance).getHost();
}
