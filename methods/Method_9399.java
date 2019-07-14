public static boolean checkSecret(byte[] secret,Long id){
  if (secret == null || secret.length != 32) {
    return false;
  }
  int sum=0;
  int a;
  for (a=0; a < secret.length; a++) {
    sum+=secret[a] & 0xff;
  }
  if (sum % 255 != 239) {
    return false;
  }
  if (id != null && Utilities.bytesToLong(Utilities.computeSHA256(secret)) != id) {
    return false;
  }
  return true;
}
