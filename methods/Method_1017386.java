@Override public boolean validate(@NotNull WebServerConfig server,@NotNull WebServerConfig.RemotePath remotePath){
  return remotePath.path.toLowerCase().endsWith(this.fileExtension.toLowerCase());
}
