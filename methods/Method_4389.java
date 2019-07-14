private static byte[] adjustRequestData(UUID uuid,byte[] requestData){
  if (C.CLEARKEY_UUID.equals(uuid)) {
    return ClearKeyUtil.adjustRequestData(requestData);
  }
  return requestData;
}
