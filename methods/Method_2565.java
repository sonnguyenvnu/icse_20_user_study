/** 
 * Calculates the length of the the sub-path in a _transition path, that is used only by a given string.
 * @param str a String corresponding to a _transition path from sourceNode
 * @return an int denoting the size of the sub-path in the _transition pathcorresponding to  {@code str} that is only used by {@code str}
 */
private int calculateSoleTransitionPathLength(String str){
  Stack<MDAGNode> transitionPathNodeStack=sourceNode.getTransitionPathNodes(str);
  transitionPathNodeStack.pop();
  transitionPathNodeStack.trimToSize();
  while (!transitionPathNodeStack.isEmpty()) {
    MDAGNode currentNode=transitionPathNodeStack.peek();
    if (currentNode.getOutgoingTransitions().size() <= 1 && !currentNode.isAcceptNode())     transitionPathNodeStack.pop();
 else     break;
  }
  return (transitionPathNodeStack.capacity() - transitionPathNodeStack.size());
}
