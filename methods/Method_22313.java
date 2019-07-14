@Override @Nullable public final KeyStore create(@NonNull Context context){
  final InputStream inputStream=getInputStream(context);
  if (inputStream != null) {
    final BufferedInputStream bufferedInputStream=new BufferedInputStream(inputStream);
    try {
      final KeyStore keyStore=KeyStore.getInstance(getKeyStoreType());
switch (getStreamType()) {
case CERTIFICATE:
        final CertificateFactory certificateFactory=CertificateFactory.getInstance(certificateType);
      final Certificate certificate=certificateFactory.generateCertificate(bufferedInputStream);
    keyStore.load(null,null);
  keyStore.setCertificateEntry("ca",certificate);
break;
case KEYSTORE:
keyStore.load(bufferedInputStream,getPassword());
}
return keyStore;
}
 catch (CertificateException e) {
ACRA.log.e(LOG_TAG,"Could not load certificate",e);
}
catch (KeyStoreException e) {
ACRA.log.e(LOG_TAG,"Could not load keystore",e);
}
catch (NoSuchAlgorithmException e) {
ACRA.log.e(LOG_TAG,"Could not load keystore",e);
}
catch (IOException e) {
ACRA.log.e(LOG_TAG,"Could not load keystore",e);
}
 finally {
IOUtils.safeClose(bufferedInputStream);
}
}
return null;
}
