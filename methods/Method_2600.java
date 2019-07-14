/** 
 * ????????<br> Removes a _transition labeled with a given char. This only removes the connection between this node and the _transition's target node; the target node is not deleted.
 * @param letter        the char labeling the _transition of interest
 */
public void removeOutgoingTransition(char letter){
  outgoingTransitionTreeMap.remove(letter);
}
