private synchronized void initialize(){
  if (null != instance) {
    final String errMsg="ConfigurationManagementGraph should be instantiated just once, by the JanusGraphManager.";
    throw new ConfigurationManagementGraphAlreadyInstantiatedException(errMsg);
  }
  instance=this;
}
