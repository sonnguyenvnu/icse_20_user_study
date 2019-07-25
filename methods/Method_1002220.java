@Override public boolean test(String url){
  if (url.contains("get_video_info") || url.contains("googlevideo")) {
    if (mCachedDeviceMatchResult == null) {
      mCachedDeviceMatchResult=Helpers.deviceMatch(mDevicesToProcess);
    }
    return mCachedDeviceMatchResult;
  }
  return false;
}
