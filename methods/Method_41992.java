private DrmSessionManager<FrameworkMediaCrypto> buildDrmSessionManagerV18(UUID uuid,String licenseUrl,String[] keyRequestPropertiesArray,boolean multiSession) throws UnsupportedDrmException {
  HttpMediaDrmCallback drmCallback=new HttpMediaDrmCallback(licenseUrl,buildHttpDataSourceFactory(false));
  if (keyRequestPropertiesArray != null) {
    for (int i=0; i < keyRequestPropertiesArray.length - 1; i+=2) {
      drmCallback.setKeyRequestProperty(keyRequestPropertiesArray[i],keyRequestPropertiesArray[i + 1]);
    }
  }
  return new DefaultDrmSessionManager<>(uuid,FrameworkMediaDrm.newInstance(uuid),drmCallback,null,mainHandler,null,multiSession);
}
