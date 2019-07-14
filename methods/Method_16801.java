public State captureRemoteData(String urlStr){
  HttpURLConnection connection=null;
  URL url=null;
  String suffix=null;
  try {
    url=new URL(urlStr);
    if (!validHost(url.getHost())) {
      return new BaseState(false,AppInfo.PREVENT_HOST);
    }
    connection=(HttpURLConnection)url.openConnection();
    connection.setInstanceFollowRedirects(true);
    connection.setUseCaches(true);
    if (!validContentState(connection.getResponseCode())) {
      return new BaseState(false,AppInfo.CONNECTION_ERROR);
    }
    suffix=MIMEType.getSuffix(connection.getContentType());
    if (!validFileType(suffix)) {
      return new BaseState(false,AppInfo.NOT_ALLOW_FILE_TYPE);
    }
    if (!validFileSize(connection.getContentLength())) {
      return new BaseState(false,AppInfo.MAX_SIZE);
    }
    String savePath=this.getPath(this.savePath,this.filename,suffix);
    String physicalPath=this.rootPath + savePath;
    State state=StorageManager.saveFileByInputStream(connection.getInputStream(),physicalPath);
    if (state.isSuccess()) {
      state.putInfo("url",PathFormat.format(savePath));
      state.putInfo("source",urlStr);
    }
    return state;
  }
 catch (  Exception e) {
    return new BaseState(false,AppInfo.REMOTE_FAIL);
  }
}
