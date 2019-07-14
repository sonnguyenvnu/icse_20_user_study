/** 
 * Determines whether this node has an outgoing _transition with a given label.
 * @param letter        the char labeling the desired _transition
 * @return              true if this node possesses a _transition labeled with{@code letter}, and false otherwise
 */
public boolean hasOutgoingTransition(char letter){
  return outgoingTransitionTreeMap.containsKey(letter);
}
