public static String encrypt(String password){
  return new SimpleHash(ALGORITH_NAME,password,ByteSource.Util.bytes(password),HASH_ITERATIONS).toHex();
}
