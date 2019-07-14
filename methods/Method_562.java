public byte[] bytesValue(){
  if (token == JSONToken.HEX) {
    throw new JSONException("TODO");
  }
  return IOUtils.decodeBase64(buf,np + 1,sp);
}
