public static BigInteger getV(byte[] passwordBytes,TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow algo){
  BigInteger g=BigInteger.valueOf(algo.g);
  byte[] g_bytes=getBigIntegerBytes(g);
  BigInteger p=new BigInteger(1,algo.p);
  byte[] x_bytes=getX(passwordBytes,algo);
  BigInteger x=new BigInteger(1,x_bytes);
  return g.modPow(x,p);
}
