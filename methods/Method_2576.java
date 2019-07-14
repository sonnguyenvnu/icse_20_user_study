/** 
 * ??????<br>
 * @param current ??????
 * @param fromIndex ????
 * @param toNodeArray ????
 * @param fromNodeArray ?????????????????
 */
private void createMDAGNode(SimpleMDAGNode current,int fromIndex,MDAGNode[] toNodeArray,MDAGNode[] fromNodeArray){
  MDAGNode from=(fromIndex == -1 ? sourceNode : toNodeArray[fromIndex]);
  int transitionSetBegin=current.getTransitionSetBeginIndex();
  int onePastTransitionSetEnd=transitionSetBegin + current.getOutgoingTransitionSetSize();
  for (int i=transitionSetBegin; i < onePastTransitionSetEnd; i++) {
    SimpleMDAGNode targetNode=mdagDataArray[i];
    if (toNodeArray[i] != null) {
      fromNodeArray[fromIndex].addOutgoingTransition(current.getLetter(),fromNodeArray[i]);
      toNodeArray[fromIndex]=fromNodeArray[i];
      continue;
    }
    toNodeArray[i]=from.addOutgoingTransition(targetNode.getLetter(),targetNode.isAcceptNode());
    fromNodeArray[i]=from;
    createMDAGNode(targetNode,i,toNodeArray,fromNodeArray);
  }
}
