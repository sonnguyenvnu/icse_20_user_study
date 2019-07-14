private static SSLContext buildAllowAnythingSSLContext(){
  try {
    return SSLContexts.custom().loadTrustMaterial(null,new TrustStrategy(){
      @Override public boolean isTrusted(      X509Certificate[] chain,      String authType) throws CertificateException {
        return true;
      }
    }
).build();
  }
 catch (  Exception e) {
    return throwUnchecked(e,SSLContext.class);
  }
}
