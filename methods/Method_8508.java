public void checkCamera(boolean request){
  if (baseFragment == null) {
    return;
  }
  boolean old=deviceHasGoodCamera;
  if (!SharedConfig.inappCamera) {
    deviceHasGoodCamera=false;
  }
 else {
    if (Build.VERSION.SDK_INT >= 23) {
      if (baseFragment.getParentActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
        if (request) {
          try {
            baseFragment.getParentActivity().requestPermissions(new String[]{Manifest.permission.CAMERA},17);
          }
 catch (          Exception ignore) {
          }
        }
        deviceHasGoodCamera=false;
      }
 else {
        if (request || SharedConfig.hasCameraCache) {
          CameraController.getInstance().initCamera(null);
        }
        deviceHasGoodCamera=CameraController.getInstance().isCameraInitied();
      }
    }
 else {
      if (request || SharedConfig.hasCameraCache) {
        CameraController.getInstance().initCamera(null);
      }
      deviceHasGoodCamera=CameraController.getInstance().isCameraInitied();
    }
  }
  if (old != deviceHasGoodCamera && photoAttachAdapter != null) {
    photoAttachAdapter.notifyDataSetChanged();
  }
  if (isShowing() && deviceHasGoodCamera && baseFragment != null && backDrawable.getAlpha() != 0 && !revealAnimationInProgress && !cameraOpened) {
    showCamera();
  }
}
