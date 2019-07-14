private void light(){
  if (mFlashing) {
    mFlashing=false;
    CameraManager.get().openLight();
  }
 else {
    mFlashing=true;
    CameraManager.get().offLight();
  }
}
