protected synchronized void initializePerformanceMonitoring(@Nullable ImagePerfDataListener imagePerfDataListener){
  if (mImagePerfMonitor != null) {
    mImagePerfMonitor.reset();
  }
  if (imagePerfDataListener != null) {
    if (mImagePerfMonitor == null) {
      mImagePerfMonitor=new ImagePerfMonitor(AwakeTimeSinceBootClock.get(),this);
    }
    mImagePerfMonitor.addImagePerfDataListener(imagePerfDataListener);
    mImagePerfMonitor.setEnabled(true);
  }
}
