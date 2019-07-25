/** 
 * Tells an item fullfill the search method.
 * @param item    the item to test
 * @param pattern the pattern
 * @return true if match the search method
 */
private static boolean match(ParticipantAdapterItem item,String pattern){
  return item.startsWith(pattern);
}
