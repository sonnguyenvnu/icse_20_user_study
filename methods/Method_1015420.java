/** 
 * Prints a representation of the node defined by  {@code fqn}. Output includes name, fqn and data.
 */
public String print(String fqn){
  Node n=findNode(fqn);
  if (n == null)   return null;
  return n.toString();
}
