public static byte[] truncate(byte[] in,int length){
  if (in.length == length)   return in;
  return Arrays.copyOfRange(in,0,length);
}
