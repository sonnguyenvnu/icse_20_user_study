/** 
 * set a child node as the alignment controller when applying alignments on the nodes list.
 * @param node
 * @param child
 */
public static void alignNodeToChild(Node node,Node child){
  setConstraint(node,ALIGN_NODE_CONSTRAINT,child);
}
