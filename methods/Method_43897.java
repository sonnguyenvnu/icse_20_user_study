/** 
 * Gets the set of available currency codes. 
 */
public static SortedSet<String> getAvailableCurrencyCodes(){
  return new TreeSet<>(currencies.keySet());
}
