public ID3v2TagBody tagBody(InputStream input) throws IOException, ID3v2Exception {
  if (compression) {
    throw new ID3v2Exception("Tag compression is not supported");
  }
  if (version < 4 && unsynchronization) {
    byte[] bytes=new ID3v2DataInput(input).readFully(totalTagSize - headerSize);
    boolean ff=false;
    byte ffByte=(byte)0xFF;
    int len=0;
    for (    byte b : bytes) {
      if (!ff || b != 0) {
        bytes[len++]=b;
      }
      ff=(b == ffByte);
    }
    return new ID3v2TagBody(new ByteArrayInputStream(bytes,0,len),headerSize,len,this);
  }
 else {
    return new ID3v2TagBody(input,headerSize,totalTagSize - headerSize - footerSize,this);
  }
}
