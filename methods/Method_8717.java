private boolean initCamera(){
  ArrayList<CameraInfo> cameraInfos=CameraController.getInstance().getCameras();
  if (cameraInfos == null) {
    return false;
  }
  CameraInfo notFrontface=null;
  for (int a=0; a < cameraInfos.size(); a++) {
    CameraInfo cameraInfo=cameraInfos.get(a);
    if (!cameraInfo.isFrontface()) {
      notFrontface=cameraInfo;
    }
    if (isFrontface && cameraInfo.isFrontface() || !isFrontface && !cameraInfo.isFrontface()) {
      selectedCamera=cameraInfo;
      break;
    }
 else {
      notFrontface=cameraInfo;
    }
  }
  if (selectedCamera == null) {
    selectedCamera=notFrontface;
  }
  if (selectedCamera == null) {
    return false;
  }
  ArrayList<Size> previewSizes=selectedCamera.getPreviewSizes();
  ArrayList<Size> pictureSizes=selectedCamera.getPictureSizes();
  previewSize=CameraController.chooseOptimalSize(previewSizes,480,270,aspectRatio);
  pictureSize=CameraController.chooseOptimalSize(pictureSizes,480,270,aspectRatio);
  if (previewSize.mWidth != pictureSize.mWidth) {
    boolean found=false;
    for (int a=previewSizes.size() - 1; a >= 0; a--) {
      Size preview=previewSizes.get(a);
      for (int b=pictureSizes.size() - 1; b >= 0; b--) {
        Size picture=pictureSizes.get(b);
        if (preview.mWidth >= pictureSize.mWidth && preview.mHeight >= pictureSize.mHeight && preview.mWidth == picture.mWidth && preview.mHeight == picture.mHeight) {
          previewSize=preview;
          pictureSize=picture;
          found=true;
          break;
        }
      }
      if (found) {
        break;
      }
    }
    if (!found) {
      for (int a=previewSizes.size() - 1; a >= 0; a--) {
        Size preview=previewSizes.get(a);
        for (int b=pictureSizes.size() - 1; b >= 0; b--) {
          Size picture=pictureSizes.get(b);
          if (preview.mWidth >= 240 && preview.mHeight >= 240 && preview.mWidth == picture.mWidth && preview.mHeight == picture.mHeight) {
            previewSize=preview;
            pictureSize=picture;
            found=true;
            break;
          }
        }
        if (found) {
          break;
        }
      }
    }
  }
  if (BuildVars.LOGS_ENABLED) {
    FileLog.d("preview w = " + previewSize.mWidth + " h = " + previewSize.mHeight);
  }
  return true;
}
