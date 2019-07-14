public byte[] bytesValue(){
  if (token == JSONToken.HEX) {
    int start=np + 1, len=sp;
    if (len % 2 != 0) {
      throw new JSONException("illegal state. " + len);
    }
    byte[] bytes=new byte[len / 2];
    for (int i=0; i < bytes.length; ++i) {
      char c0=text.charAt(start + i * 2);
      char c1=text.charAt(start + i * 2 + 1);
      int b0=c0 - (c0 <= 57 ? 48 : 55);
      int b1=c1 - (c1 <= 57 ? 48 : 55);
      bytes[i]=(byte)((b0 << 4) | b1);
    }
    return bytes;
  }
  return IOUtils.decodeBase64(text,np + 1,sp);
}
