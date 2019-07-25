private SafeClusterStateTaskListener safe(ClusterStateTaskListener listener){
  if (listener instanceof AckedClusterStateTaskListener) {
    return new SafeAckedClusterStateTaskListener((AckedClusterStateTaskListener)listener,logger);
  }
 else {
    return new SafeClusterStateTaskListener(listener,logger);
  }
}
