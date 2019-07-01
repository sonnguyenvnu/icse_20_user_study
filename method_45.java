/** 
 * Get the maximum interval between successive speculative read last add confirmed and entry requests.
 * @return the max speculative request timeout in milliseconds. Default 5000.
 */
public int _XXXXX_(){
  return getInt(MAX_SPECULATIVE_READ_LAC_TIMEOUT,5000);
}