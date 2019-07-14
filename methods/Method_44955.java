private static String getRandomSecretKey(){
  byte[] array=new byte[7];
  new Random().nextBytes(array);
  return new String(array,Charset.forName("UTF-8"));
}
