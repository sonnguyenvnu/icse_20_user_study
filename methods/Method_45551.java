/** 
 * Build key.
 * @return the string
 */
@Override public String buildKey(){
  return interfaceId + ":" + uniqueId;
}
