@Override void setFacing(@Facing int facing){
synchronized (mCameraLock) {
    int internalFacing=new ConstantMapper.Facing(facing).map();
    if (internalFacing == -1) {
      return;
    }
    for (int i=0, count=Camera.getNumberOfCameras(); i < count; i++) {
      Camera.getCameraInfo(i,mCameraInfo);
      if (mCameraInfo.facing == internalFacing) {
        mCameraId=i;
        mFacing=facing;
        break;
      }
    }
    if (mFacing == facing && isCameraOpened()) {
      stop();
      start();
    }
  }
}
