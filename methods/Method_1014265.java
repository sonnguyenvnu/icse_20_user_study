private void init(String hostAddress,int connectionTimeout,int readTimeout,String username,String password,String applicationToken,boolean acceptAllCerts){
  config=new Config(hostAddress,username,password,applicationToken);
  if (connectionTimeout >= 0) {
    config.setConnectionTimeout(connectionTimeout);
  }
  if (readTimeout >= 0) {
    config.setReadTimeout(readTimeout);
  }
  init(config,acceptAllCerts);
}
