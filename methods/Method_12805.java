public static boolean verifyZip(Context context,String zipPath,String cerName){
  try {
    CertificateFactory certificateFactory=CertificateFactory.getInstance("X.509");
    InputStream in=context.getAssets().open(cerName);
    Certificate certificate=certificateFactory.generateCertificate(in);
    in.close();
    return verifyZip(zipPath,certificate);
  }
 catch (  IOException|CertificateException e) {
    Log.w(Constants.TAG,e);
    return false;
  }
}
