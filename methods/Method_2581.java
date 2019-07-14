/** 
 * ????<br> Retrieves all the Strings in the MDAG that begin with a given String.
 * @param prefixStr a String that is the prefix for all the desired Strings
 * @return a HashSet containing all the Strings present in the MDAG that begin with {@code prefixString}
 */
public HashSet<String> getStringsStartingWith(String prefixStr){
  HashSet<String> strHashSet=new HashSet<String>();
  if (sourceNode != null) {
    MDAGNode originNode=sourceNode.transition(prefixStr);
    if (originNode != null) {
      if (originNode.isAcceptNode())       strHashSet.add(prefixStr);
      getStrings(strHashSet,SearchCondition.PREFIX_SEARCH_CONDITION,prefixStr,prefixStr,originNode.getOutgoingTransitions());
    }
  }
 else {
    SimpleMDAGNode originNode=SimpleMDAGNode.traverseMDAG(mdagDataArray,simplifiedSourceNode,prefixStr);
    if (originNode != null) {
      if (originNode.isAcceptNode())       strHashSet.add(prefixStr);
      getStrings(strHashSet,SearchCondition.PREFIX_SEARCH_CONDITION,prefixStr,prefixStr,originNode);
    }
  }
  return strHashSet;
}
