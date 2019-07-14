public static String encode(String password){
  try {
    MessageDigest md5=MessageDigest.getInstance("MD5");
    byte[] byteArray=md5.digest(password.getBytes("utf-8"));
    String passwordMD5=byteArrayToHexString(byteArray);
    return passwordMD5;
  }
 catch (  Exception e) {
    e.fillInStackTrace();
  }
  return password;
}
