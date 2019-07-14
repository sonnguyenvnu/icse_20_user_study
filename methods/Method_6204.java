public String readZeroTerminatedString(int maxLength,ID3v2Encoding encoding) throws IOException, ID3v2Exception {
  int zeros=0;
  int length=Math.min(maxLength,(int)getRemainingLength());
  byte[] bytes=textBuffer.get().bytes(length);
  for (int i=0; i < length; i++) {
    if ((bytes[i]=data.readByte()) == 0 && (encoding != ID3v2Encoding.UTF_16 || zeros != 0 || i % 2 == 0)) {
      if (++zeros == encoding.getZeroBytes()) {
        return extractString(bytes,0,i + 1 - encoding.getZeroBytes(),encoding,false);
      }
    }
 else {
      zeros=0;
    }
  }
  throw new ID3v2Exception("Could not read zero-termiated string");
}
