@Override public byte[] executeKeyRequest(UUID uuid,KeyRequest request) throws Exception {
  String url=request.getLicenseServerUrl();
  if (forceDefaultLicenseUrl || TextUtils.isEmpty(url)) {
    url=defaultLicenseUrl;
  }
  Map<String,String> requestProperties=new HashMap<>();
  String contentType=C.PLAYREADY_UUID.equals(uuid) ? "text/xml" : (C.CLEARKEY_UUID.equals(uuid) ? "application/json" : "application/octet-stream");
  requestProperties.put("Content-Type",contentType);
  if (C.PLAYREADY_UUID.equals(uuid)) {
    requestProperties.put("SOAPAction","http://schemas.microsoft.com/DRM/2007/03/protocols/AcquireLicense");
  }
synchronized (keyRequestProperties) {
    requestProperties.putAll(keyRequestProperties);
  }
  return executePost(dataSourceFactory,url,request.getData(),requestProperties);
}
