/** 
 * Determines whether this node has any outgoing transitions.
 * @return      true if this node has at least one outgoing _transition, false otherwise
 */
public boolean hasTransitions(){
  return !outgoingTransitionTreeMap.isEmpty();
}
