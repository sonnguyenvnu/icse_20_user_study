@Override public synchronized void _XXXXX_(byte[] b,int off,int len){
  if (!makeSpace(len)) {
    bbytes=new byte[len];
    bytebuff=ByteBuffer.wrap(bbytes);
  }
  bytebuff.put(b,off,len);
}