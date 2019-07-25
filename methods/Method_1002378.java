private void die(Callback<?> callback,String serviceName,String message){
  _serviceUnavailableStats.inc();
  callback.onError(new ServiceUnavailableException(serviceName,message));
}
