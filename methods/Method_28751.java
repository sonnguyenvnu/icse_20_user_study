/** 
 * Retrieve the configuration of a running Redis server. Not all the configuration parameters are supported. <p> CONFIG GET returns the current configuration parameters. This sub command only accepts a single argument, that is glob style pattern. All the configuration parameters matching this parameter are reported as a list of key-value pairs. <p> <b>Example:</b> <pre> $ redis-cli config get '*' 1. "dbfilename" 2. "dump.rdb" 3. "requirepass" 4. (nil) 5. "masterauth" 6. (nil) 7. "maxmemory" 8. "0\n" 9. "appendfsync" 10. "everysec" 11. "save" 12. "3600 1 300 100 60 10000" $ redis-cli config get 'm*' 1. "masterauth" 2. (nil) 3. "maxmemory" 4. "0\n" </pre>
 * @param pattern
 * @return Bulk reply.
 */
@Override public List<String> configGet(final String pattern){
  client.configGet(pattern);
  return client.getMultiBulkReply();
}
