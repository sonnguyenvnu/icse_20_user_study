@Override public String download(QiniuContent content,QiniuConfig config){
  String finalUrl=null;
  if (TYPE.equals(content.getType())) {
    finalUrl=content.getUrl();
  }
 else {
    Auth auth=Auth.create(config.getAccessKey(),config.getSecretKey());
    long expireInSeconds=3600;
    finalUrl=auth.privateDownloadUrl(content.getUrl(),expireInSeconds);
  }
  return finalUrl;
}
