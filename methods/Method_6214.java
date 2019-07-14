public ID3v2FrameBody frameBody(ID3v2FrameHeader frameHeader) throws IOException, ID3v2Exception {
  int dataLength=frameHeader.getBodySize();
  InputStream input=this.input;
  if (frameHeader.isUnsynchronization()) {
    byte[] bytes=data.readFully(frameHeader.getBodySize());
    boolean ff=false;
    byte ffByte=(byte)0xFF;
    int len=0;
    for (    byte b : bytes) {
      if (!ff || b != 0) {
        bytes[len++]=b;
      }
      ff=(b == ffByte);
    }
    dataLength=len;
    input=new ByteArrayInputStream(bytes,0,len);
  }
  if (frameHeader.isEncryption()) {
    throw new ID3v2Exception("Frame encryption is not supported");
  }
  if (frameHeader.isCompression()) {
    dataLength=frameHeader.getDataLengthIndicator();
    input=new InflaterInputStream(input);
  }
  return new ID3v2FrameBody(input,frameHeader.getHeaderSize(),dataLength,tagHeader,frameHeader);
}
