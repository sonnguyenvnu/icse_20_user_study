/** 
 * Checks whether a given node exists in the tree
 * @param fqn The fully qualified name of the node
 * @return boolean Whether or not the node exists
 */
public boolean exists(String fqn){
  return fqn != null && findNode(fqn) != null;
}
