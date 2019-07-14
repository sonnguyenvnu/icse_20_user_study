private static SSLContext buildSSLContextWithTrustStore(KeyStoreSettings trustStoreSettings){
  try {
    KeyStore trustStore=trustStoreSettings.loadStore();
    return SSLContexts.custom().loadTrustMaterial(null,new TrustSelfSignedStrategy()).loadKeyMaterial(trustStore,trustStoreSettings.password().toCharArray()).useTLS().build();
  }
 catch (  Exception e) {
    return throwUnchecked(e,SSLContext.class);
  }
}
