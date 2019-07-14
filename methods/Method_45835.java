public byte[] allocInputBuffer(int minSize){
  byte[] buf=inputBuffer;
  if (buf == null || buf.length < minSize) {
    buf=new byte[Math.max(minSize,MIN_OUTPUT_BUFFER)];
  }
 else {
    inputBuffer=null;
  }
  return buf;
}
