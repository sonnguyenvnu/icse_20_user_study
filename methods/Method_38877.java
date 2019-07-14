/** 
 * Removes all child nodes. Each child node will be detached from this parent.
 */
public void removeAllChilds(){
  List<Node> removedNodes=childNodes;
  childNodes=null;
  childElementNodes=null;
  childElementNodesCount=0;
  if (removedNodes != null) {
    for (int i=0, removedNodesSize=removedNodes.size(); i < removedNodesSize; i++) {
      Node removedNode=removedNodes.get(i);
      removedNode.detachFromParent();
    }
  }
}
