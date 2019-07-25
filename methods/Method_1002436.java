/** 
 * Returns whether the symbol is a member of the enum.
 * @param symbol to check.
 * @return true if symbol is a member of the enum.
 */
public boolean contains(String symbol){
  return _symbolToIndexMap.containsKey(symbol);
}
