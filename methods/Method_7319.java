public static byte[] computePBKDF2(byte[] password,byte[] salt){
  byte[] dst=new byte[64];
  Utilities.pbkdf2(password,salt,dst,100000);
  return dst;
}
