public static boolean verifyZip(String zipPath,Certificate remoteCertificate){
  try {
    String certPath=checkZipFileForCertificate(zipPath);
    Certificate certificate=getCertificateFromZip(zipPath,certPath);
    remoteCertificate.verify(certificate.getPublicKey());
    return true;
  }
 catch (  Exception e) {
    Log.w(Constants.TAG,e);
    return false;
  }
}
