/** 
 * Sets if new threads will be daemon.
 */
public ThreadFactoryBuilder setDaemon(final boolean daemon){
  this.daemonThread=daemon;
  return this;
}
