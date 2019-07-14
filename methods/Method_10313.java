/** 
 * Gets a KeyStore containing the Certificate
 * @param cert InputStream of the Certificate
 * @return KeyStore
 */
public static KeyStore getKeystoreOfCA(InputStream cert){
  InputStream caInput=null;
  Certificate ca=null;
  try {
    CertificateFactory cf=CertificateFactory.getInstance("X.509");
    caInput=new BufferedInputStream(cert);
    ca=cf.generateCertificate(caInput);
  }
 catch (  CertificateException e1) {
    e1.printStackTrace();
  }
 finally {
    try {
      if (caInput != null) {
        caInput.close();
      }
    }
 catch (    IOException e) {
      e.printStackTrace();
    }
  }
  String keyStoreType=KeyStore.getDefaultType();
  KeyStore keyStore=null;
  try {
    keyStore=KeyStore.getInstance(keyStoreType);
    keyStore.load(null,null);
    keyStore.setCertificateEntry("ca",ca);
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return keyStore;
}
