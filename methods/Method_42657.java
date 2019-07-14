public static ClientKeyStore loadClientKeyStore(String keyStorePath,String keyStorePass,String privateKeyPass){
  try {
    return loadClientKeyStore(new FileInputStream(keyStorePath),keyStorePass,privateKeyPass);
  }
 catch (  Exception e) {
    logger.error("loadClientKeyFactory fail : " + e.getMessage(),e);
    return null;
  }
}
