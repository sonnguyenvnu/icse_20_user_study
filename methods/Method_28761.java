/** 
 * <pre> redis 127.0.0.1:26381&gt; sentinel slaves mymaster 1)  1) "name" 2) "127.0.0.1:6380" 3) "ip" 4) "127.0.0.1" 5) "port" 6) "6380" 7) "runid" 8) "d7f6c0ca7572df9d2f33713df0dbf8c72da7c039" 9) "flags" 10) "slave" 11) "pending-commands" 12) "0" 13) "last-ok-ping-reply" 14) "47" 15) "last-ping-reply" 16) "47" 17) "info-refresh" 18) "657" 19) "master-link-down-time" 20) "0" 21) "master-link-status" 22) "ok" 23) "master-host" 24) "localhost" 25) "master-port" 26) "6379" 27) "slave-priority" 28) "100" </pre>
 * @param masterName
 * @return
 */
@Override @SuppressWarnings("rawtypes") public List<Map<String,String>> sentinelSlaves(String masterName){
  client.sentinel(Protocol.SENTINEL_SLAVES,masterName);
  final List<Object> reply=client.getObjectMultiBulkReply();
  final List<Map<String,String>> slaves=new ArrayList<Map<String,String>>();
  for (  Object obj : reply) {
    slaves.add(BuilderFactory.STRING_MAP.build((List)obj));
  }
  return slaves;
}
