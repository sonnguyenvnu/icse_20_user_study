protected boolean bufferUpdate(){
  BufferData data=null;
  try {
    data=bufferCache.remove(0);
  }
 catch (  NoSuchElementException ex) {
    PGraphics.showWarning("Don't have pixel data to copy to texture");
  }
  if (data != null) {
    if ((data.w != width) || (data.h != height)) {
      init(data.w,data.h);
    }
    data.rgbBuf.rewind();
    setNative(data.rgbBuf,0,0,width,height);
    if (usedBuffers == null) {
      usedBuffers=new LinkedList<BufferData>();
    }
    usedBuffers.add(data);
    return true;
  }
 else {
    return false;
  }
}
