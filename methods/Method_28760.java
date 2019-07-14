/** 
 * <pre> redis 127.0.0.1:26381&gt; sentinel reset mymaster (integer) 1 </pre>
 * @param pattern
 * @return
 */
@Override public Long sentinelReset(String pattern){
  client.sentinel(Protocol.SENTINEL_RESET,pattern);
  return client.getIntegerReply();
}
