/** 
 * ????<br> Retrieves all the Strings in the MDAG that begin with a given String.
 * @param suffixStr a String that is the suffix for all the desired Strings
 * @return a HashSet containing all the Strings present in the MDAG that end with {@code suffixStr}
 */
public HashSet<String> getStringsEndingWith(String suffixStr){
  HashSet<String> strHashSet=new HashSet<String>();
  if (sourceNode != null)   getStrings(strHashSet,SearchCondition.SUFFIX_SEARCH_CONDITION,suffixStr,"",sourceNode.getOutgoingTransitions());
 else   getStrings(strHashSet,SearchCondition.SUFFIX_SEARCH_CONDITION,suffixStr,"",simplifiedSourceNode);
  return strHashSet;
}
