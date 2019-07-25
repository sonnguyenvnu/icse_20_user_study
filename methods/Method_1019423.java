public static SSLConfiguration load(String filePath) throws FileNotFoundException, IOException {
  checkNotNull(filePath,"Argument [filePath] may not be null");
  logger.debug("load SSL configuration file:{}",filePath);
  KeyStoreInfo keyStoreInfo=null;
  KeyStoreInfo trustKeyStoreInfo=null;
  Properties properties=new Properties();
  properties.load(new FileInputStream(filePath));
  String keystorePath=PathUtil.getAbstractPath(properties.getProperty("ssl.keystore.location"));
  String password=properties.getProperty("ssl.keystore.password");
  String type=properties.getProperty("ssl.keystore.type","JSK");
  String trustKeystorePath=PathUtil.getAbstractPath(properties.getProperty("ssl.trustStore.location"));
  String trustPassword=properties.getProperty("ssl.trustStore.password");
  String trustType=properties.getProperty("ssl.trustStore.type","JSK");
  if (!Strings.isNullOrEmpty(keystorePath)) {
    keyStoreInfo=new KeyStoreInfo(keystorePath,password,type);
  }
  if (!Strings.isNullOrEmpty(trustKeystorePath)) {
    trustKeyStoreInfo=new KeyStoreInfo(trustKeystorePath,trustPassword,trustType);
  }
  String clientAuthValue=properties.getProperty("ssl.client.auth","false");
  boolean clientAuth=false;
  if (clientAuthValue.equalsIgnoreCase("true")) {
    clientAuth=true;
  }
  return new SSLConfiguration(keyStoreInfo,trustKeyStoreInfo,clientAuth);
}
