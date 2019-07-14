public static byte[] getX(byte[] passwordBytes,TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow algo){
  byte[] x_bytes=Utilities.computeSHA256(algo.salt1,passwordBytes,algo.salt1);
  x_bytes=Utilities.computeSHA256(algo.salt2,x_bytes,algo.salt2);
  x_bytes=Utilities.computePBKDF2(x_bytes,algo.salt1);
  return Utilities.computeSHA256(algo.salt2,x_bytes,algo.salt2);
}
