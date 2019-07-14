public static String[] emojify(byte[] sha256){
  if (sha256.length != 32)   throw new IllegalArgumentException("sha256 needs to be exactly 32 bytes");
  String[] result=new String[5];
  for (int i=0; i < 5; i++) {
    result[i]=emojis[bytesToInt(sha256,offsets[i]) % emojis.length];
  }
  return result;
}
