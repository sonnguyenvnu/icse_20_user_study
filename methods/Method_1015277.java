public static String encode(String password,String salt){
  String resultString=null;
  try {
    resultString=password + salt;
    MessageDigest md=MessageDigest.getInstance("md5");
    resultString=byteArrayToString(md.digest(resultString.getBytes()));
  }
 catch (  Exception ex) {
    ex.printStackTrace();
  }
  return resultString;
}
