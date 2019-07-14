protected byte getStencilValue(int scrX,int scrY){
  if (stencilBuffer == null) {
    stencilBuffer=ByteBuffer.allocate(1);
  }
  stencilBuffer.rewind();
  readPixels(scrX,graphics.height - scrY - 1,1,1,STENCIL_INDEX,UNSIGNED_BYTE,stencilBuffer);
  return stencilBuffer.get(0);
}
