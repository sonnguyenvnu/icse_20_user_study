public void copyBufferFromSource(Object natRef,ByteBuffer byteBuf,int w,int h){
  if (bufferCache == null) {
    bufferCache=new LinkedList<BufferData>();
  }
  if (bufferCache.size() + 1 <= MAX_BUFFER_CACHE_SIZE) {
    bufferCache.add(new BufferData(natRef,byteBuf.asIntBuffer(),w,h));
  }
 else {
    try {
      usedBuffers.add(new BufferData(natRef,byteBuf.asIntBuffer(),w,h));
    }
 catch (    Exception e) {
      e.printStackTrace();
    }
  }
}
