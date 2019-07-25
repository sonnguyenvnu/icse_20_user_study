private void die(String serviceName,String message) throws ServiceUnavailableException {
  _serviceUnavailableStats.inc();
  throw new ServiceUnavailableException(serviceName,message);
}
