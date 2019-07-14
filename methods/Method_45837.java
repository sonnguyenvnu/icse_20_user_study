public byte[] allocDecodeBuffer(int size){
  byte[] buf=decodingBuffer;
  if (buf == null || buf.length < size) {
    buf=new byte[size];
  }
 else {
    decodingBuffer=null;
  }
  return buf;
}
