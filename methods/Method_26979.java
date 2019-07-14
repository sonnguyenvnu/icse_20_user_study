@Override void stop(){
  if (mCaptureSession != null) {
    mCaptureSession.close();
    mCaptureSession=null;
  }
  if (mCamera != null) {
    mCamera.close();
    mCamera=null;
  }
  if (mImageReader != null) {
    mImageReader.close();
    mImageReader=null;
  }
}
