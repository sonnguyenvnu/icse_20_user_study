protected String toString(ServiceInstance instance){
  String httpScheme=instance.isSecure() ? "https" : "http";
  return String.format("serviceId=%s, instanceId=%s, url= %s://%s:%d",instance.getServiceId(),instance.getInstanceId(),instance.getScheme() != null ? instance.getScheme() : httpScheme,instance.getHost(),instance.getPort());
}
