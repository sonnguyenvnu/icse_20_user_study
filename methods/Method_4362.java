private void postKeyRequest(byte[] scope,int type,boolean allowRetry){
  try {
    currentKeyRequest=mediaDrm.getKeyRequest(scope,schemeDatas,type,optionalKeyRequestParameters);
    postRequestHandler.post(MSG_KEYS,currentKeyRequest,allowRetry);
  }
 catch (  Exception e) {
    onKeysError(e);
  }
}
