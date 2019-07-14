/** 
 * Execute the given template with SshClientTemplate.
 * @param template {@link SFtpClientTemplate} to execute
 * @param < T > Type of return value
 * @return Template execution results
 */
public static final <T>T execute(@NonNull final SFtpClientTemplate template){
  return execute(new SshClientTemplate(template.url,false){
    @Override public T execute(    SSHClient client){
      SFTPClient sftpClient=null;
      T retval=null;
      try {
        sftpClient=client.newSFTPClient();
        retval=template.execute(sftpClient);
      }
 catch (      IOException e) {
        Log.e(TAG,"Error executing template method",e);
      }
 finally {
        if (sftpClient != null && template.closeClientOnFinish) {
          try {
            sftpClient.close();
          }
 catch (          IOException e) {
            Log.w(TAG,"Error closing SFTP client",e);
          }
        }
      }
      return retval;
    }
  }
);
}
