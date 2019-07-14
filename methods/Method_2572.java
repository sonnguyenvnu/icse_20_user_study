/** 
 * ?????????????<br> Clones a _transition path from a given node.
 * @param pivotConfluenceNode         the MDAGNode that the cloning operation is to be based from
 * @param transitionStringToPivotNode a String which corresponds with a _transition path from souceNode to {@code pivotConfluenceNode}
 * @param str                         a String which corresponds to the _transition path from {@code pivotConfluenceNode} that is to be cloned
 */
private void cloneTransitionPath(MDAGNode pivotConfluenceNode,String transitionStringToPivotNode,String str){
  MDAGNode lastTargetNode=pivotConfluenceNode.transition(str);
  MDAGNode lastClonedNode=null;
  char lastTransitionLabelChar='\0';
  for (int i=str.length(); i >= 0; i--) {
    String currentTransitionString=(i > 0 ? str.substring(0,i) : null);
    MDAGNode currentTargetNode=(i > 0 ? pivotConfluenceNode.transition(currentTransitionString) : pivotConfluenceNode);
    MDAGNode clonedNode;
    if (i == 0) {
      String transitionStringToPivotNodeParent=transitionStringToPivotNode.substring(0,transitionStringToPivotNode.length() - 1);
      char parentTransitionLabelChar=transitionStringToPivotNode.charAt(transitionStringToPivotNode.length() - 1);
      clonedNode=pivotConfluenceNode.clone(sourceNode.transition(transitionStringToPivotNodeParent),parentTransitionLabelChar);
    }
 else     clonedNode=currentTargetNode.clone();
    transitionCount+=clonedNode.getOutgoingTransitionCount();
    if (lastClonedNode != null) {
      clonedNode.reassignOutgoingTransition(lastTransitionLabelChar,lastTargetNode,lastClonedNode);
      lastTargetNode=currentTargetNode;
    }
    lastClonedNode=clonedNode;
    lastTransitionLabelChar=(i > 0 ? str.charAt(i - 1) : '\0');
  }
}
