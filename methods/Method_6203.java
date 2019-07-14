private String extractString(byte[] bytes,int offset,int length,ID3v2Encoding encoding,boolean searchZeros){
  if (searchZeros) {
    int zeros=0;
    for (int i=0; i < length; i++) {
      if (bytes[offset + i] == 0 && (encoding != ID3v2Encoding.UTF_16 || zeros != 0 || (offset + i) % 2 == 0)) {
        if (++zeros == encoding.getZeroBytes()) {
          length=i + 1 - encoding.getZeroBytes();
          break;
        }
      }
 else {
        zeros=0;
      }
    }
  }
  try {
    String string=new String(bytes,offset,length,encoding.getCharset().name());
    if (string.length() > 0 && string.charAt(0) == '\uFEFF') {
      string=string.substring(1);
    }
    return string;
  }
 catch (  Exception e) {
    return "";
  }
}
