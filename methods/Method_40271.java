/** 
 * Every visited node is passed to this method.  The semantics for halting traversal are the same as for  {@link DefaultNodeVisitor}.
 * @return {@code true} to traverse this node's children
 */
public boolean dispatch(Node n){
  return traverseIntoNodes;
}
