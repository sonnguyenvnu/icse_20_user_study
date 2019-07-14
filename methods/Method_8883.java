public Bitmap getBitmap(){
  return eglThread != null ? eglThread.getTexture() : null;
}
