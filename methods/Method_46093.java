private void watchHealthService(){
  try {
    HealthServicesRequest request=HealthServicesRequest.newBuilder().setTag(tag).setQueryParams(new QueryParams(properties.getWatchTimeout(),currentData.getConsulIndex())).setPassing(true).build();
    Response<List<HealthService>> response=consulClient.getHealthServices(serviceName,request);
    if (response.getConsulIndex().equals(currentData.getConsulIndex())) {
      return;
    }
    this.currentData=response;
    ProviderGroup providerGroup=new ProviderGroup(currentProviders());
    listeners.stream().filter(Objects::nonNull).forEach(l -> l.updateProviders(providerGroup));
  }
 catch (  Exception e) {
    LOGGER.error("Consul watch health service failed.",e);
  }
}
