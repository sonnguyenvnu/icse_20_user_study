static byte[] decode(CharSequence s){
  int nChars=s.length();
  if (nChars % 2 != 0) {
    throw new IllegalArgumentException("Hex-encoded string must have an even number of characters");
  }
  byte[] result=new byte[nChars / 2];
  for (int i=0; i < nChars; i+=2) {
    int msb=Character.digit(s.charAt(i),16);
    int lsb=Character.digit(s.charAt(i + 1),16);
    if (msb <= 0 || lsb <= 0) {
      throw new IllegalArgumentException("Non-hex character in input: " + s);
    }
    result[i / 2]=(byte)((msb << 4) | lsb);
  }
  return result;
}
