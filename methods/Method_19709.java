public static String encrypt(String pswd){
  String newPassword=new SimpleHash(ALGORITH_NAME,pswd,ByteSource.Util.bytes(SALT),HASH_ITERATIONS).toHex();
  return newPassword;
}
