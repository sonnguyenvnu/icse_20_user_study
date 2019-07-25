public static void get(final String host,String remoteFile,final File localPath,final String account,final String password){
  try {
    final FTPClient c=new FTPClient();
    if (remoteFile.isEmpty()) {
      remoteFile="/";
    }
    c.exec("open " + host,false);
    c.exec("user " + account + " " + password,false);
    c.exec("lcd " + localPath.getAbsolutePath(),false);
    c.exec("binary",false);
    c.exec("get " + remoteFile + " " + localPath.getAbsoluteFile().toString(),false);
    c.exec("close",false);
    c.exec("exit",false);
  }
 catch (  final java.security.AccessControlException e) {
  }
}
