/** 
 * ????????????? Reassigns the target node of one of this node's outgoing transitions.
 * @param letter            the char which labels the outgoing _transition of interest
 * @param oldTargetNode     the MDAGNode that is currently the target of the _transition of interest
 * @param newTargetNode     the MDAGNode that is to be the target of the _transition of interest
 */
public void reassignOutgoingTransition(char letter,MDAGNode oldTargetNode,MDAGNode newTargetNode){
  oldTargetNode.incomingTransitionCount--;
  newTargetNode.incomingTransitionCount++;
  outgoingTransitionTreeMap.put(letter,newTargetNode);
}
