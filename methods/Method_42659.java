public static TrustKeyStore loadTrustKeyStore(String keyStorePath,String keyStorePass){
  try {
    return loadTrustKeyStore(new FileInputStream(keyStorePath),keyStorePass);
  }
 catch (  Exception e) {
    logger.error("loadTrustCertFactory fail : " + e.getMessage(),e);
    return null;
  }
}
