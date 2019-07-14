/** 
 * Default Cache TTL, can be overridden
 * @return memory persister ttl
 */
static long getCacheTTL(){
  return TimeUnit.HOURS.toSeconds(24);
}
