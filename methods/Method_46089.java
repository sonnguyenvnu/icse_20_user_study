private NewService.Check buildCheck(String serverHost,int serverPort){
  NewService.Check check=new NewService.Check();
  ConsulRegistryProperties.HealthCheckType healthCheckType=properties.getHealthCheckType();
  if (healthCheckType == ConsulRegistryProperties.HealthCheckType.TTL) {
    check.setTtl(properties.getHealthCheckTTL());
  }
 else   if (healthCheckType == ConsulRegistryProperties.HealthCheckType.TCP) {
    String host=properties.getHealthCheckHost(serverHost);
    int port=properties.getHealthCheckPort(serverPort);
    check.setTcp(host + ":" + port);
    check.setInterval(properties.getHealthCheckInterval());
    check.setTimeout(properties.getHealthCheckTimeout());
  }
 else {
    String host=properties.getHealthCheckHost(serverHost);
    int port=properties.getHealthCheckPort(serverPort);
    String address;
    try {
      address=new URL(properties.getHealthCheckProtocol(),host,port,properties.getHealthCheckPath()).toString();
    }
 catch (    Exception e) {
      throw new SofaRpcRuntimeException("Invalid health check url!",e);
    }
    check.setHttp(address);
    check.setMethod(properties.getHealthCheckMethod());
    check.setInterval(properties.getHealthCheckInterval());
    check.setTimeout(properties.getHealthCheckTimeout());
  }
  return check;
}
