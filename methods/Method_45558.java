/** 
 * add server.
 * @param server ServerConfig
 * @return the ProviderConfig
 */
public ProviderConfig<T> setServer(ServerConfig server){
  if (this.server == null) {
    this.server=new ArrayList<ServerConfig>();
  }
  this.server.add(server);
  return this;
}
