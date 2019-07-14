/** 
 * ????key<br> Retrieves all the valid Strings that have been inserted in to the MDAG.
 * @return a HashSet containing all the Strings that have been inserted into the MDAG
 */
public HashSet<String> getAllStrings(){
  HashSet<String> strHashSet=new LinkedHashSet<String>();
  if (sourceNode != null)   getStrings(strHashSet,SearchCondition.NO_SEARCH_CONDITION,null,"",sourceNode.getOutgoingTransitions());
 else   getStrings(strHashSet,SearchCondition.NO_SEARCH_CONDITION,null,"",simplifiedSourceNode);
  return strHashSet;
}
