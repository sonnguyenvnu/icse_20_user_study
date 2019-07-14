/** 
 * Generates unique controller id. 
 */
protected static String generateUniqueControllerId(){
  return String.valueOf(sIdCounter.getAndIncrement());
}
