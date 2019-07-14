public static Certificate getCertificateFromZip(String zipPath,String certPath) throws Exception {
  CertificateFactory certificateFactory=CertificateFactory.getInstance("X.509");
  ZipFile zip=new ZipFile(new File(zipPath));
  InputStream in=zip.getInputStream(zip.getEntry(certPath));
  Certificate certificate=certificateFactory.generateCertificates(in).iterator().next();
  in.close();
  zip.close();
  return certificate;
}
