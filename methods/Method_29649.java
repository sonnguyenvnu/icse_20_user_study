@Override void modifyZoom(float modifier){
synchronized (mCameraLock) {
    setZoom(this.mZoom * modifier);
  }
}
