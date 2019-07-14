/** 
 * ?????????????? Decrements (by 1) the incoming _transition counts of all of the nodes that are targets of outgoing transitions from this node.
 */
public void decrementTargetIncomingTransitionCounts(){
  for (  Entry<Character,MDAGNode> transitionKeyValuePair : outgoingTransitionTreeMap.entrySet())   transitionKeyValuePair.getValue().incomingTransitionCount--;
}
