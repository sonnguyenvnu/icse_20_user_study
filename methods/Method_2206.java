@Nullable public synchronized ImageDebugData getDebugDataForResourceId(String resourceId){
  for (  ImageDebugData debugData : mImageRequestDebugDataMap.values()) {
    Set<String> ids=debugData.getResourceIds();
    if (ids != null && ids.contains(resourceId)) {
      return debugData;
    }
  }
  return null;
}
