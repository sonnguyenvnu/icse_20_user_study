public boolean hasBitmap(){
  return nativePtr != 0 && (renderingBitmap != null || nextRenderingBitmap != null);
}
