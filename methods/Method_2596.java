/** 
 * ???????????????<br> Retrieves the nodes in the _transition path starting from this node corresponding to a given String .
 * @param str       a String corresponding to a _transition path starting from this node
 * @return          a Stack of MDAGNodes containing the nodes in the _transition pathdenoted by  {@code str}, in the order they are encountered in during transitioning
 */
public Stack<MDAGNode> getTransitionPathNodes(String str){
  Stack<MDAGNode> nodeStack=new Stack<MDAGNode>();
  MDAGNode currentNode=this;
  int numberOfChars=str.length();
  for (int i=0; i < numberOfChars && currentNode != null; i++) {
    currentNode=currentNode.transition(str.charAt(i));
    nodeStack.add(currentNode);
  }
  return nodeStack;
}
