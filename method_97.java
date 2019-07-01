/** 
 * Get local scrub rate limit (entries/second).
 * @return Max number of entries to scrub per second, 0 for disabled.
 */
public double _XXXXX_(){
  return this.getDouble(LOCAL_SCRUB_RATE_LIMIT,60);
}