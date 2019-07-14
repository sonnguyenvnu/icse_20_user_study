/** 
 * remove the thread pool information
 * @param serverConfig server config
 */
public void removeThreadPool(ServerConfig serverConfig){
  Lookout.registry().removeMetric(rpcLookoutId.removeServerThreadConfigId(serverConfig));
  Lookout.registry().removeMetric(rpcLookoutId.removeServerThreadPoolActiveCountId(serverConfig));
  Lookout.registry().removeMetric(rpcLookoutId.removeServerThreadPoolIdleCountId(serverConfig));
  Lookout.registry().removeMetric(rpcLookoutId.removeServerThreadPoolQueueSizeId(serverConfig));
}
