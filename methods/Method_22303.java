@SuppressWarnings("WeakerAccess") protected void configureHttps(@NonNull HttpsURLConnection connection) throws GeneralSecurityException {
  final String algorithm=TrustManagerFactory.getDefaultAlgorithm();
  final TrustManagerFactory tmf=TrustManagerFactory.getInstance(algorithm);
  final KeyStore keyStore=KeyStoreHelper.getKeyStore(context,config);
  tmf.init(keyStore);
  final SSLContext sslContext=SSLContext.getInstance("TLS");
  sslContext.init(null,tmf.getTrustManagers(),null);
  connection.setSSLSocketFactory(sslContext.getSocketFactory());
}
