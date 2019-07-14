private void initCamera(SurfaceHolder surfaceHolder){
  try {
    CameraManager.get().openDriver(surfaceHolder);
  }
 catch (  IOException ioe) {
    return;
  }
catch (  RuntimeException e) {
    return;
  }
  if (handler == null) {
    handler=new CaptureActivityHandler(this,decodeFormats,characterSet,viewfinderView,this);
  }
}
