@Override public ServiceInfo[] list(String type,Duration timeout){
  ServiceInfo[] services=new ServiceInfo[0];
  for (  JmDNS instance : jmdnsInstances.values()) {
    services=concatenate(services,instance.list(type,timeout.toMillis()));
  }
  return services;
}
