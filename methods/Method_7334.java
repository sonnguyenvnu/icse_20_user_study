public ByteBuffer getFrame(){
  mPixelBuf.rewind();
  GLES20.glReadPixels(0,0,mWidth,mHeight,GLES20.GL_RGBA,GLES20.GL_UNSIGNED_BYTE,mPixelBuf);
  return mPixelBuf;
}
