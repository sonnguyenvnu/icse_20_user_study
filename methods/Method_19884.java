public static String encrypt(String username,String password){
  return new SimpleHash(ALGORITH_NAME,password,ByteSource.Util.bytes(username.toLowerCase() + password),HASH_ITERATIONS).toHex();
}
