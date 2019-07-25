private static void dir(final String host,final String remotePath,final String account,final String password){
  try {
    final FTPClient c=new FTPClient();
    c.exec("open " + host,false);
    c.exec("user " + account + " " + password,false);
    c.exec("cd " + remotePath,false);
    c.exec("ls",true);
    c.exec("close",false);
    c.exec("exit",false);
  }
 catch (  final java.security.AccessControlException e) {
  }
}
