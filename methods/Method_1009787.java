@Override public void create(SurfaceTexture visualSurfaceTexture,int pixelFormat,int pixelWidth,int pixelHeight,int visualWidth,int visualHeight){
  if (pixelFormat != ImageFormat.NV21) {
    throw new IllegalArgumentException("GLESRender,pixelFormat only support NV21");
  }
synchronized (syncRenderThread) {
    glesRenderThread=new GLESRenderThread(visualSurfaceTexture,pixelFormat,pixelWidth,pixelHeight,visualWidth,visualHeight);
    glesRenderThread.start();
  }
}
