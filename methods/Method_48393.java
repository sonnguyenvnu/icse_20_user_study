public static byte[] hexToBytes(String str){
  if (str.length() % 2 == 1)   throw new NumberFormatException("An hex string representing bytes must have an even length");
  byte[] bytes=new byte[str.length() / 2];
  for (int i=0; i < bytes.length; i++) {
    byte halfByte1=charToByte[str.charAt(i * 2)];
    byte halfByte2=charToByte[str.charAt(i * 2 + 1)];
    if (halfByte1 == -1 || halfByte2 == -1)     throw new NumberFormatException("Non-hex characters in " + str);
    bytes[i]=(byte)((halfByte1 << 4) | halfByte2);
  }
  return bytes;
}
