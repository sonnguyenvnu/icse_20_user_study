/** 
 * ???????????
 * @param node1
 * @param node2
 * @return
 */
public static boolean haveSameTransitions(MDAGNode node1,MDAGNode node2){
  TreeMap<Character,MDAGNode> outgoingTransitionTreeMap1=node1.outgoingTransitionTreeMap;
  TreeMap<Character,MDAGNode> outgoingTransitionTreeMap2=node2.outgoingTransitionTreeMap;
  if (outgoingTransitionTreeMap1.size() == outgoingTransitionTreeMap2.size()) {
    for (    Entry<Character,MDAGNode> transitionKeyValuePair : outgoingTransitionTreeMap1.entrySet()) {
      Character currentCharKey=transitionKeyValuePair.getKey();
      MDAGNode currentTargetNode=transitionKeyValuePair.getValue();
      if (!outgoingTransitionTreeMap2.containsKey(currentCharKey) || !outgoingTransitionTreeMap2.get(currentCharKey).equals(currentTargetNode))       return false;
    }
  }
 else   return false;
  return true;
}
