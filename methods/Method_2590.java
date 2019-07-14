/** 
 * Determines if this node is a confluence node (defined as a node with two or more incoming transitions
 * @return      true if this node has two or more incoming transitions, false otherwise
 */
public boolean isConfluenceNode(){
  return (incomingTransitionCount > 1);
}
