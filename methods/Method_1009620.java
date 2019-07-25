@Override protected void failed(GENASubscription subscription,UpnpResponse responseStatus,Exception exception,String defaultMsg){
  AVTransportControlPoint.LOGGER.severe(defaultMsg);
}
