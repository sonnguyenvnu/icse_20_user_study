private static byte[] stream(int... values){
  byte[] bytes=new byte[values.length];
  for (int i=0; i < values.length; i++) {
    bytes[i]=(byte)values[i];
  }
  return bytes;
}
