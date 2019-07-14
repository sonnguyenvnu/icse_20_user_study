public static ClientKeyStore loadClientKeyStore(InputStream keyStoreStream,String keyStorePass,String privateKeyPass){
  try {
    KeyManagerFactory kmf=KeyManagerFactory.getInstance("SunX509");
    KeyStore ks=KeyStore.getInstance("JKS");
    ks.load(keyStoreStream,keyStorePass.toCharArray());
    kmf.init(ks,privateKeyPass.toCharArray());
    return new ClientKeyStore(kmf);
  }
 catch (  Exception e) {
    logger.error("loadClientKeyFactory fail : " + e.getMessage(),e);
    return null;
  }
}
