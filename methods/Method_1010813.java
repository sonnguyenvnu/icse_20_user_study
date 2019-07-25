/** 
 * Uncomments the node which is under the comment node specified in constructor <p></p> Also if containment link of the commented node is singular and non optional comments or deletes the existing child whether it is instance of abstract concept or not respectively
 * @throws IllegalStateException if node to uncomment does not have parent
 * @return node which was under the comment
 */
public SNode uncomment(){
  if (!(isValid())) {
    throw new IllegalStateException("Node uncommenter has invalid state. Comment attribute has no parent. Attribute " + myComment.getPresentation() + " Attribute id: " + myComment.getNodeId());
  }
  SNode commentedNode=SLinkOperations.getTarget(myComment,MetaAdapterFactory.getContainmentLink(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x3dcc194340c24debL,0x2ab99f0d2248e89dL,"commentedNode"));
  if (getContainmentLink() != null && commentedNode != null) {
    removeOrCommentChildInSingleRole();
    myComment.removeChild(commentedNode);
    getParent().insertChildAfter(getContainmentLink(),commentedNode,myComment);
  }
  SNodeOperations.deleteNode(myComment);
  return commentedNode;
}
