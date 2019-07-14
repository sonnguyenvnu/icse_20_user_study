public PooledObject<FTPClient> makeObject() throws Exception {
  FTPClient ftpClient=new FTPClient();
  ftpClient.setConnectTimeout(config.getClientTimeout());
  ftpClient.connect(config.getHost(),config.getPort());
  int reply=ftpClient.getReplyCode();
  if (!FTPReply.isPositiveCompletion(reply)) {
    ftpClient.disconnect();
    logger.warn("FTPServer refused connection");
    return null;
  }
  boolean result=ftpClient.login(config.getUsername(),config.getPassword());
  if (!result) {
    throw new ConnectException("ftp????:" + config.getUsername() + "/password:" + config.getPassword() + "@" + config.getHost());
  }
  ftpClient.setFileType(config.getTransferFileType());
  ftpClient.setBufferSize(1024);
  ftpClient.setControlEncoding(config.getEncoding());
  if (config.isPassiveMode()) {
    ftpClient.enterLocalPassiveMode();
  }
  return new DefaultPooledObject<>(ftpClient);
}
