/** 
 * Build key.
 * @return the string
 */
@Override public String buildKey(){
  return protocol + "://" + interfaceId + ":" + uniqueId;
}
