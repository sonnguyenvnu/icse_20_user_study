public static TrustKeyStore loadTrustKeyStore(InputStream keyStoreStream,String keyStorePass){
  try {
    TrustManagerFactory tmf=TrustManagerFactory.getInstance("SunX509");
    KeyStore ks=KeyStore.getInstance("JKS");
    ks.load(keyStoreStream,keyStorePass.toCharArray());
    tmf.init(ks);
    return new TrustKeyStore(tmf);
  }
 catch (  Exception e) {
    logger.error("loadTrustCertFactory fail : " + e.getMessage(),e);
    return null;
  }
}
