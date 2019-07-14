@Override public void init() throws Exception {
  List<TMProperties> tmList=fastStorage.findTMProperties().stream().filter(tmProperties -> !tmProperties.getHost().equals(txManagerConfig.getHost()) || !tmProperties.getTransactionPort().equals(txManagerConfig.getPort())).collect(Collectors.toList());
  for (  TMProperties properties : tmList) {
    NotifyConnectParams notifyConnectParams=new NotifyConnectParams();
    notifyConnectParams.setHost(txManagerConfig.getHost());
    notifyConnectParams.setPort(txManagerConfig.getPort());
    String url=String.format(MANAGER_REFRESH_URL,properties.getHost(),properties.getHttpPort());
    try {
      ResponseEntity<Boolean> res=restTemplate.postForEntity(url,notifyConnectParams,Boolean.class);
      if (res.getStatusCode().equals(HttpStatus.OK) || res.getStatusCode().is5xxServerError()) {
        log.info("manager auto refresh res->{}",res);
        break;
      }
 else {
        fastStorage.removeTMProperties(properties.getHost(),properties.getTransactionPort());
      }
    }
 catch (    Exception e) {
      log.error("manager auto refresh error: {}",e.getMessage());
      if (e instanceof ResourceAccessException) {
        ResourceAccessException resourceAccessException=(ResourceAccessException)e;
        if (resourceAccessException.getCause() != null && resourceAccessException.getCause() instanceof ConnectException) {
          fastStorage.removeTMProperties(properties.getHost(),properties.getTransactionPort());
        }
      }
    }
  }
  if (StringUtils.hasText(txManagerConfig.getHost())) {
    TMProperties tmProperties=new TMProperties();
    tmProperties.setHttpPort(ApplicationInformation.serverPort(serverProperties));
    tmProperties.setHost(txManagerConfig.getHost());
    tmProperties.setTransactionPort(txManagerConfig.getPort());
    fastStorage.saveTMProperties(tmProperties);
  }
}
