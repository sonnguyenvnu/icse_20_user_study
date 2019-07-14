@Override public InputStream readFile(String fileIdOrMd5){
  return createRequest("/md5/download/" + fileIdOrMd5).get().as(OAuth2Response::asStream);
}
