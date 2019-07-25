@Override public void rendering(byte[] pixel){
  if (mVisualSurface != null && mVisualSurface.isValid()) {
    renderingSurface(mVisualSurface,pixel,mPixelWidth,mPixelHeight,mPixelSize);
  }
 else {
    LogTools.d("NativeRender,rendering()invalid Surface");
  }
}
