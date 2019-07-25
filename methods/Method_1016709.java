public StringBuilder dirhtml(String remotePath) throws IOException {
  if (isFolder(remotePath) && '/' != remotePath.charAt(remotePath.length() - 1)) {
    remotePath+='/';
  }
  final String pwd=pwd();
  final List<String> list=list(remotePath,true);
  if (this.remotesystem == null)   try {
    sys();
  }
 catch (  final IOException e) {
  }
  final String base="ftp://" + ((this.account.equals(ANONYMOUS)) ? "" : (this.account + ":" + this.password + "@")) + this.host + ((this.port == 21) ? "" : (":" + this.port)) + ((remotePath.length() > 0 && remotePath.charAt(0) == '/') ? "" : pwd + "/") + remotePath;
  return dirhtml(base,this.remotemessage,this.remotegreeting,this.remotesystem,list,true);
}
