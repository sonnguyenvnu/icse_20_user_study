/** 
 * ???????key<br> Retrieves all the Strings in the MDAG that contain a given String.
 * @param str a String that is contained in all the desired Strings
 * @return a HashSet containing all the Strings present in the MDAG that begin with {@code prefixString}
 */
public HashSet<String> getStringsWithSubstring(String str){
  HashSet<String> strHashSet=new HashSet<String>();
  if (sourceNode != null)   getStrings(strHashSet,SearchCondition.SUBSTRING_SEARCH_CONDITION,str,"",sourceNode.getOutgoingTransitions());
 else   getStrings(strHashSet,SearchCondition.SUBSTRING_SEARCH_CONDITION,str,"",simplifiedSourceNode);
  return strHashSet;
}
