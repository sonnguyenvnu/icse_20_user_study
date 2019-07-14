public static byte[] calcAuthKeyHash(byte[] auth_key){
  byte[] sha1=Utilities.computeSHA1(auth_key);
  byte[] key_hash=new byte[16];
  System.arraycopy(sha1,0,key_hash,0,16);
  return key_hash;
}
