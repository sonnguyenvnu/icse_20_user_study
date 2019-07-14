/** 
 * ??????<br> Creates an MDAGNode possessing the same accept state status and outgoing transitions as this node.
 * @return      an MDAGNode possessing the same accept state status and outgoing transitions as this node
 */
public MDAGNode clone(){
  return new MDAGNode(this);
}
