/** 
 * (??????)
 * @param timeout ??:??
 * @return
 */
public RedisSentinelBuilder setTimeout(int timeout){
  this.connectionTimeout=timeout;
  this.soTimeout=timeout;
  return this;
}
