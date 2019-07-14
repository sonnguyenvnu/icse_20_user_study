/** 
 * Optimized variant of  {@link #reindexChildren()} for addition.Only added children are optimized.
 */
protected void reindexChildrenOnAdd(final int addedCount){
  int childNodesSize=childNodes.size();
  int previousSize=childNodes.size() - addedCount;
  int siblingElementIndex=childElementNodesCount;
  for (int i=previousSize; i < childNodesSize; i++) {
    Node childNode=childNodes.get(i);
    childNode.siblingIndex=i;
    childNode.siblingNameIndex=-1;
    if (childNode.getNodeType() == NodeType.ELEMENT) {
      childNode.siblingElementIndex=siblingElementIndex;
      siblingElementIndex++;
    }
  }
  childElementNodesCount=siblingElementIndex;
  childElementNodes=null;
}
