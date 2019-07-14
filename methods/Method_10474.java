@Override void takePicture(){
  if (mAutoFocus) {
    lockFocus();
  }
 else {
    captureStillPicture();
  }
}
