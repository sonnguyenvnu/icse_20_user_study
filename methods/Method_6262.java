private void initCamera(){
  CameraInfo info=null;
  ArrayList<CameraInfo> cameraInfos=CameraController.getInstance().getCameras();
  if (cameraInfos == null) {
    return;
  }
  for (int a=0; a < cameraInfos.size(); a++) {
    CameraInfo cameraInfo=cameraInfos.get(a);
    if (isFrontface && cameraInfo.frontCamera != 0 || !isFrontface && cameraInfo.frontCamera == 0) {
      info=cameraInfo;
      break;
    }
  }
  if (info == null) {
    return;
  }
  float size4to3=4.0f / 3.0f;
  float size16to9=16.0f / 9.0f;
  float screenSize=(float)Math.max(AndroidUtilities.displaySize.x,AndroidUtilities.displaySize.y) / Math.min(AndroidUtilities.displaySize.x,AndroidUtilities.displaySize.y);
  org.telegram.messenger.camera.Size aspectRatio;
  int wantedWidth;
  int wantedHeight;
  if (initialFrontface) {
    aspectRatio=new Size(16,9);
    wantedWidth=480;
    wantedHeight=270;
  }
 else {
    if (Math.abs(screenSize - size4to3) < 0.1f) {
      aspectRatio=new Size(4,3);
      wantedWidth=1280;
      wantedHeight=960;
    }
 else {
      aspectRatio=new Size(16,9);
      wantedWidth=1280;
      wantedHeight=720;
    }
  }
  if (textureView.getWidth() > 0 && textureView.getHeight() > 0) {
    int width=Math.min(AndroidUtilities.displaySize.x,AndroidUtilities.displaySize.y);
    int height=width * aspectRatio.getHeight() / aspectRatio.getWidth();
    previewSize=CameraController.chooseOptimalSize(info.getPreviewSizes(),width,height,aspectRatio);
  }
  org.telegram.messenger.camera.Size pictureSize=CameraController.chooseOptimalSize(info.getPictureSizes(),wantedWidth,wantedHeight,aspectRatio);
  if (pictureSize.getWidth() >= 1280 && pictureSize.getHeight() >= 1280) {
    if (Math.abs(screenSize - size4to3) < 0.1f) {
      aspectRatio=new Size(3,4);
    }
 else {
      aspectRatio=new Size(9,16);
    }
    org.telegram.messenger.camera.Size pictureSize2=CameraController.chooseOptimalSize(info.getPictureSizes(),wantedHeight,wantedWidth,aspectRatio);
    if (pictureSize2.getWidth() < 1280 || pictureSize2.getHeight() < 1280) {
      pictureSize=pictureSize2;
    }
  }
  SurfaceTexture surfaceTexture=textureView.getSurfaceTexture();
  if (previewSize != null && surfaceTexture != null) {
    surfaceTexture.setDefaultBufferSize(previewSize.getWidth(),previewSize.getHeight());
    cameraSession=new CameraSession(info,previewSize,pictureSize,ImageFormat.JPEG);
    CameraController.getInstance().open(cameraSession,surfaceTexture,new Runnable(){
      @Override public void run(){
        if (cameraSession != null) {
          cameraSession.setInitied();
        }
        checkPreviewMatrix();
      }
    }
,new Runnable(){
      @Override public void run(){
        if (delegate != null) {
          delegate.onCameraCreated(cameraSession.cameraInfo.camera);
        }
      }
    }
);
  }
}
