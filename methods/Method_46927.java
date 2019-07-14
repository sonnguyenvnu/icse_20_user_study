@Override public void deliverResult(AppsDataPair data){
  if (isReset()) {
    if (data != null)     onReleaseResources(data);
  }
  AppsDataPair oldData=mApps;
  mApps=data;
  if (isStarted()) {
    super.deliverResult(mApps);
  }
  if (oldData != null) {
    onReleaseResources(oldData);
  }
}
