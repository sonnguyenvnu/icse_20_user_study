/** 
 * Called by AgeOutCache, to removed expired connections
 * @param key
 */
public void expired(Address key){
  if (key != null) {
    log.debug("%s: removing expired connection to %s",local_addr,key);
    closeConnection(key);
  }
}
