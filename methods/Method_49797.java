/** 
 * A preorder traversal is done in the following order: <ul> <li> Visit root. <li> Traverse children from left to right in preorder. </ul> This method fills the live node list.
 * @param The root of preorder traversal
 * @return The next match
 */
private void fillList(Node node){
  if (node == mRootNode) {
    mSearchNodes=new ArrayList<Node>();
  }
 else {
    if ((mTagName == null) || node.getNodeName().equals(mTagName)) {
      mSearchNodes.add(node);
    }
  }
  node=node.getFirstChild();
  while (node != null) {
    if (mDeepSearch) {
      fillList(node);
    }
 else {
      if ((mTagName == null) || node.getNodeName().equals(mTagName)) {
        mSearchNodes.add(node);
      }
    }
    node=node.getNextSibling();
  }
}
