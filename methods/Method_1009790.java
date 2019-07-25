@Override public void create(SurfaceTexture visualSurfaceTexture,int pixelFormat,int pixelWidth,int pixelHeight,int visualWidth,int visualHeight){
  if (pixelFormat != ImageFormat.NV21) {
    throw new IllegalArgumentException("NativeRender,pixelFormat only support NV21");
  }
  mVisualSurface=new Surface(visualSurfaceTexture);
  mPixelWidth=pixelWidth;
  mPixelHeight=pixelHeight;
  mPixelSize=(3 * pixelWidth * pixelHeight) / 2;
}
