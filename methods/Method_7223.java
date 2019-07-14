public static TLRPC.TL_inputCheckPasswordSRP startCheck(byte[] x_bytes,long srp_id,byte[] srp_B,TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow algo){
  if (x_bytes == null || srp_B == null || srp_B.length == 0 || !Utilities.isGoodPrime(algo.p,algo.g)) {
    return null;
  }
  BigInteger g=BigInteger.valueOf(algo.g);
  byte[] g_bytes=getBigIntegerBytes(g);
  BigInteger p=new BigInteger(1,algo.p);
  byte[] k_bytes=Utilities.computeSHA256(algo.p,g_bytes);
  BigInteger k=new BigInteger(1,k_bytes);
  BigInteger x=new BigInteger(1,x_bytes);
  byte[] a_bytes=new byte[256];
  Utilities.random.nextBytes(a_bytes);
  BigInteger a=new BigInteger(1,a_bytes);
  BigInteger A=g.modPow(a,p);
  byte[] A_bytes=getBigIntegerBytes(A);
  BigInteger B=new BigInteger(1,srp_B);
  if (B.compareTo(BigInteger.ZERO) <= 0 || B.compareTo(p) >= 0) {
    return null;
  }
  byte[] B_bytes=getBigIntegerBytes(B);
  byte[] u_bytes=Utilities.computeSHA256(A_bytes,B_bytes);
  BigInteger u=new BigInteger(1,u_bytes);
  if (u.compareTo(BigInteger.ZERO) == 0) {
    return null;
  }
  BigInteger B_kgx=B.subtract(k.multiply(g.modPow(x,p)).mod(p));
  if (B_kgx.compareTo(BigInteger.ZERO) < 0) {
    B_kgx=B_kgx.add(p);
  }
  if (!Utilities.isGoodGaAndGb(B_kgx,p)) {
    return null;
  }
  BigInteger S=B_kgx.modPow(a.add(u.multiply(x)),p);
  byte[] S_bytes=getBigIntegerBytes(S);
  byte[] K_bytes=Utilities.computeSHA256(S_bytes);
  byte[] p_hash=Utilities.computeSHA256(algo.p);
  byte[] g_hash=Utilities.computeSHA256(g_bytes);
  for (int i=0; i < p_hash.length; i++) {
    p_hash[i]=(byte)(g_hash[i] ^ p_hash[i]);
  }
  TLRPC.TL_inputCheckPasswordSRP result=new TLRPC.TL_inputCheckPasswordSRP();
  result.M1=Utilities.computeSHA256(p_hash,Utilities.computeSHA256(algo.salt1),Utilities.computeSHA256(algo.salt2),A_bytes,B_bytes,K_bytes);
  result.A=A_bytes;
  result.srp_id=srp_id;
  return result;
}
