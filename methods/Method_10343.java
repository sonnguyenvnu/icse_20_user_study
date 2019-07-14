private byte[] createContentDisposition(String key,String fileName){
  return (AsyncHttpClient.HEADER_CONTENT_DISPOSITION + ": form-data; name=\"" + key + "\"" + "; filename=\"" + fileName + "\"" + STR_CR_LF).getBytes();
}
