/** 
 * <pre> redis 127.0.0.1:26381&gt; sentinel get-master-addr-by-name mymaster 1) "127.0.0.1" 2) "6379" </pre>
 * @param masterName
 * @return two elements list of strings : host and port.
 */
@Override public List<String> sentinelGetMasterAddrByName(String masterName){
  client.sentinel(Protocol.SENTINEL_GET_MASTER_ADDR_BY_NAME,masterName);
  final List<Object> reply=client.getObjectMultiBulkReply();
  return BuilderFactory.STRING_LIST.build(reply);
}
