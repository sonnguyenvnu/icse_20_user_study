/** 
 * set order according to node begin line and begin column
 * @param node node to sort
 * @return generated index
 */
public static int generateIndex(Node node){
  return (node.getBeginLine() << 16) + node.getBeginColumn();
}
