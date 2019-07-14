/** 
 * Reindex children nodes. Must be called on every children addition/removal. Iterates  {@link #childNodes} list and:<ul> <li>calculates three different sibling indexes,</li> <li>calculates total child element node count,</li> <li>resets child element nodes array (will be init lazy later by @{#initChildElementNodes}.</li> </ul>
 */
protected void reindexChildren(){
  int siblingElementIndex=0;
  for (int i=0, childNodesSize=childNodes.size(); i < childNodesSize; i++) {
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
