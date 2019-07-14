@Override public KeyRequest getKeyRequest(byte[] scope,@Nullable List<DrmInitData.SchemeData> schemeDatas,int keyType,@Nullable HashMap<String,String> optionalParameters) throws NotProvisionedException {
  SchemeData schemeData=null;
  byte[] initData=null;
  String mimeType=null;
  if (schemeDatas != null) {
    schemeData=getSchemeData(uuid,schemeDatas);
    initData=adjustRequestInitData(uuid,Assertions.checkNotNull(schemeData.data));
    mimeType=adjustRequestMimeType(uuid,schemeData.mimeType);
  }
  MediaDrm.KeyRequest request=mediaDrm.getKeyRequest(scope,initData,mimeType,keyType,optionalParameters);
  byte[] requestData=adjustRequestData(uuid,request.getData());
  String licenseServerUrl=request.getDefaultUrl();
  if (TextUtils.isEmpty(licenseServerUrl) && schemeData != null && !TextUtils.isEmpty(schemeData.licenseServerUrl)) {
    licenseServerUrl=schemeData.licenseServerUrl;
  }
  return new KeyRequest(requestData,licenseServerUrl);
}
