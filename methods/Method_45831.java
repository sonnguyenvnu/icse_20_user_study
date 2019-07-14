public byte[] allocOutputBuffer(int minSize){
  byte[] buf=outputBuffer;
  if (buf == null || buf.length < minSize) {
    buf=new byte[Math.max(minSize,MIN_OUTPUT_BUFFER)];
  }
 else {
    outputBuffer=null;
  }
  return buf;
}
