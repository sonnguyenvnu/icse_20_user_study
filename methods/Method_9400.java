private byte[] getRandomSecret(){
  byte[] secret=new byte[32];
  Utilities.random.nextBytes(secret);
  int sum=0;
  int a;
  for (a=0; a < secret.length; a++) {
    sum+=secret[a] & 0xff;
  }
  sum=sum % 255;
  if (sum != 239) {
    sum=239 - sum;
    a=Utilities.random.nextInt(32);
    int val=secret[a] & 0xff;
    val+=sum;
    if (val < 255) {
      val=255 + val;
    }
    secret[a]=(byte)(val % 255);
  }
  return secret;
}
