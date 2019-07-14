/** 
 * Obtain a  {@link SSHClient} connection from the underlying connection pool.Beneath it will return the connection if it exists; otherwise it will create a new one and put it into the connection pool. Different from  {@link #getConnection(String)} above, this accepts broken down parameters asconvenience method during setting up SCP/SFTP connection.
 * @param host host name/IP, required
 * @param port SSH server port, required
 * @param hostFingerprint expected host fingerprint, required
 * @param username username, required
 * @param password password, required if using password to authenticate
 * @param keyPair {@link KeyPair}, required if using key-based authentication
 * @return {@link SSHClient} connection
 */
public SSHClient getConnection(@NonNull String host,int port,@NonNull String hostFingerprint,@NonNull String username,@Nullable String password,@Nullable KeyPair keyPair){
  String url=SshClientUtils.deriveSftpPathFrom(host,port,username,password,keyPair);
  SSHClient client=connections.get(url);
  if (client == null) {
    client=create(host,port,hostFingerprint,username,password,keyPair);
    if (client != null)     connections.put(url,client);
  }
 else {
    if (!validate(client)) {
      Log.d(TAG,"Connection no longer usable. Reconnecting...");
      expire(client);
      connections.remove(url);
      client=create(host,port,hostFingerprint,username,password,keyPair);
      if (client != null)       connections.put(url,client);
    }
  }
  return client;
}
