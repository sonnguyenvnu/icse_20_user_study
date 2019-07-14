/** 
 * Retrieves Strings corresponding to all valid _transition paths from a given node that satisfy a given condition.
 * @param strHashSet              a HashSet of Strings to contain all those in the MDAG satisfying{@code searchCondition} with {@code conditionString}
 * @param searchCondition         the SearchCondition enum field describing the type of relationship that Strings contained in the MDAGmust have with  {@code conditionString} in order to be included in the result set
 * @param searchConditionString   the String that all Strings in the MDAG must be related with in the fashion denotedby  {@code searchCondition} in order to be included in the result set
 * @param prefixString            the String corresponding to the currently traversed _transition path
 * @param node                    an int denoting the starting index of a SimpleMDAGNode's _transition set in mdagDataArray
 */
private void getStrings(HashSet<String> strHashSet,SearchCondition searchCondition,String searchConditionString,String prefixString,SimpleMDAGNode node){
  int transitionSetBegin=node.getTransitionSetBeginIndex();
  int onePastTransitionSetEnd=transitionSetBegin + node.getOutgoingTransitionSetSize();
  for (int i=transitionSetBegin; i < onePastTransitionSetEnd; i++) {
    SimpleMDAGNode currentNode=mdagDataArray[i];
    String newPrefixString=prefixString + currentNode.getLetter();
    if (currentNode.isAcceptNode() && searchCondition.satisfiesCondition(newPrefixString,searchConditionString))     strHashSet.add(newPrefixString);
    getStrings(strHashSet,searchCondition,searchConditionString,newPrefixString,currentNode);
  }
}
