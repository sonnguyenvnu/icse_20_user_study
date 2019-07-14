private void initCamera(SurfaceHolder surfaceHolder){
  try {
    CameraManager.get().openDriver(surfaceHolder);
    Point point=CameraManager.get().getCameraResolution();
    AtomicInteger width=new AtomicInteger(point.y);
    AtomicInteger height=new AtomicInteger(point.x);
    int cropWidth=mCropLayout.getWidth() * width.get() / mContainer.getWidth();
    int cropHeight=mCropLayout.getHeight() * height.get() / mContainer.getHeight();
    setCropWidth(cropWidth);
    setCropHeight(cropHeight);
  }
 catch (  IOException|RuntimeException ioe) {
    return;
  }
  if (handler == null) {
    handler=new CaptureActivityHandler();
  }
}
