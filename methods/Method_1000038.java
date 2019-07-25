private static byte[] compose(byte[] key,byte[] addrHash){
  byte[] result=new byte[key.length];
  arraycopy(addrHash,0,result,0,PREFIX_BYTES);
  arraycopy(key,PREFIX_BYTES,result,PREFIX_BYTES,PREFIX_BYTES);
  return result;
}
