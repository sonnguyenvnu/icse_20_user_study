/** 
 * Convenience method for checking expiration
 * @return true if the expiration is befor ethe current time
 */
public boolean isExpired(){
  return expiration != null && expiration.before(new Date());
}
