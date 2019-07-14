/** 
 * Obtain a  {@link SSHClient} connection from the underlying connection pool.Beneath it will return the connection if it exists; otherwise it will create a new one and put it into the connection pool.
 * @param url SSH connection URL, in the form of <code>ssh://&lt;username&gt;:&lt;password&gt;@&lt;host&gt;:&lt;port&gt;</code> or <code>ssh://&lt;username&gt;@&lt;host&gt;:&lt;port&gt;</code>
 * @return {@link SSHClient} connection, already opened and authenticated
 * @throws IOException IOExceptions that occur during connection setup
 */
public SSHClient getConnection(@NonNull String url){
  url=SshClientUtils.extractBaseUriFrom(url);
  SSHClient client=connections.get(url);
  if (client == null) {
    client=create(url);
    if (client != null)     connections.put(url,client);
  }
 else {
    if (!validate(client)) {
      Log.d(TAG,"Connection no longer usable. Reconnecting...");
      expire(client);
      connections.remove(url);
      client=create(url);
      if (client != null)       connections.put(url,client);
    }
  }
  return client;
}
