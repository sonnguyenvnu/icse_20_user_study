public byte[] allocEncodingBuffer(int minSize){
  byte[] buf=encodingBuffer;
  if (buf == null || buf.length < minSize) {
    buf=new byte[Math.max(minSize,MIN_ENCODING_BUFFER)];
  }
 else {
    encodingBuffer=null;
  }
  return buf;
}
