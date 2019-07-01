/** 
 * Stop XMPP listener & disconnect from all XMPP Servers
 */
public void _XXXXX_(){
  if (workerPool != null && !workerPool.isShutdown()) {
    workerPool.shutdown();
  }
}