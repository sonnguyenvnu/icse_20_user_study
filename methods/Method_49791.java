public Node getPreviousSibling(){
  if ((mParentNode != null) && (this != mParentNode.getFirstChild())) {
    Vector<Node> siblings=((NodeImpl)mParentNode).mChildNodes;
    int indexOfThis=siblings.indexOf(this);
    return siblings.elementAt(indexOfThis - 1);
  }
  return null;
}
