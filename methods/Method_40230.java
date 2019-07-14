@NotNull public static String getContentHash(byte[] fileContents){
  MessageDigest algorithm;
  try {
    algorithm=MessageDigest.getInstance("SHA-1");
  }
 catch (  Exception e) {
    _.die("Failed to get SHA, shouldn't happen");
    return "";
  }
  algorithm.reset();
  algorithm.update(fileContents);
  byte messageDigest[]=algorithm.digest();
  StringBuilder sb=new StringBuilder();
  for (  byte aMessageDigest : messageDigest) {
    sb.append(String.format("%02x",0xFF & aMessageDigest));
  }
  return sb.toString();
}
