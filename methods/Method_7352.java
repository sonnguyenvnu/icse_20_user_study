public static String[] emojifyForCall(byte[] sha256){
  String[] result=new String[4];
  for (int i=0; i < 4; i++) {
    result[i]=emojis[(int)(bytesToLong(sha256,8 * i) % emojis.length)];
  }
  return result;
}
