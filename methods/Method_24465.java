public void getBufferPixels(int[] pixels){
  BufferData data=null;
  if (usedBuffers != null && 0 < usedBuffers.size()) {
    data=usedBuffers.getLast();
  }
 else   if (bufferCache != null && 0 < bufferCache.size()) {
    data=bufferCache.getLast();
  }
  if (data != null) {
    if ((data.w != width) || (data.h != height)) {
      init(data.w,data.h);
    }
    data.rgbBuf.rewind();
    data.rgbBuf.get(pixels);
    convertToARGB(pixels);
    if (usedBuffers == null) {
      usedBuffers=new LinkedList<BufferData>();
    }
    while (0 < bufferCache.size()) {
      data=bufferCache.remove(0);
      usedBuffers.add(data);
    }
  }
}
