private void releaseCamera(){
  if (mCamera != null) {
    mCamera.release();
    mCamera=null;
    mCallback.onCameraClosed();
  }
}
