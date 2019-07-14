@SuppressLint("MissingPermission") private void startIfReady() throws IOException {
  if (startRequested && surfaceAvailable) {
    cameraSource.start();
    if (overlay != null) {
      Size size=cameraSource.getPreviewSize();
      int min=Math.min(size.getWidth(),size.getHeight());
      int max=Math.max(size.getWidth(),size.getHeight());
      if (isPortraitMode()) {
        overlay.setCameraInfo(min,max,cameraSource.getCameraFacing());
      }
 else {
        overlay.setCameraInfo(max,min,cameraSource.getCameraFacing());
      }
      overlay.clear();
    }
    startRequested=false;
  }
}
