/** 
 * Execute the given SshClientTemplate. This template pattern is borrowed from Spring Framework, to simplify code on operations using SftpClientTemplate.
 * @param template {@link SshClientTemplate} to execute
 * @param < T > Type of return value
 * @return Template execution results
 */
public static final <T>T execute(@NonNull SshClientTemplate template){
  SSHClient client=null;
  T retval=null;
  try {
    client=SshConnectionPool.getInstance().getConnection(template.url);
    if (client != null)     retval=template.execute(client);
 else     throw new RuntimeException("Unable to execute template");
  }
 catch (  Exception e) {
    Log.e(TAG,"Error executing template method",e);
  }
 finally {
    if (client != null && template.closeClientOnFinish) {
      tryDisconnect(client);
    }
  }
  return retval;
}
