/** 
 * ??buffer????size???
 * @param size
 */
@Override protected void ensureAvailableBytes(int size){
  if (offset + size > bufferSize) {
    try {
      int availableBytes=(int)(fileChannel.size() - fileChannel.position());
      ByteBuffer byteBuffer=ByteBuffer.allocate(Math.min(availableBytes,offset));
      int readBytes=fileChannel.read(byteBuffer);
      if (readBytes == availableBytes) {
        fileChannel.close();
        fileChannel=null;
      }
      assert readBytes > 0 : "????????";
      byteBuffer.flip();
      byte[] bytes=byteBuffer.array();
      System.arraycopy(this.bytes,offset,this.bytes,offset - readBytes,bufferSize - offset);
      System.arraycopy(bytes,0,this.bytes,bufferSize - readBytes,readBytes);
      offset-=readBytes;
    }
 catch (    IOException e) {
      throw new RuntimeException(e);
    }
  }
}
