static String SHA1(InputStream is){
  try {
    MessageDigest messageDigest=MessageDigest.getInstance("SHA1");
    final byte[] buffer=new byte[DEFAULT_BUFFER_SIZE];
    for (int read; (read=is.read(buffer)) != -1; ) {
      messageDigest.update(buffer,0,read);
    }
    Formatter formatter=new Formatter();
    for (    final byte b : messageDigest.digest()) {
      formatter.format("%02x",b);
    }
    return formatter.toString();
  }
 catch (  NoSuchAlgorithmException e) {
    Log.e(e);
  }
catch (  IOException e) {
    Log.e(e);
  }
 finally {
    Util.close(is);
  }
  return null;
}
