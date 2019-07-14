/** 
 * Retrieves this node's outgoing transitions.
 * @return      a TreeMap containing entries collectively representingall of this node's outgoing transitions
 */
public TreeMap<Character,MDAGNode> getOutgoingTransitions(){
  return outgoingTransitionTreeMap;
}
