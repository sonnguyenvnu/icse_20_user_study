/** 
 * ???????????????<br> Removes from equivalenceClassMDAGNodeHashmap the entries of all the nodes in a _transition path.
 * @param str a String corresponding to a _transition path from sourceNode
 */
private void removeTransitionPathRegisterEntries(String str){
  MDAGNode currentNode=sourceNode;
  int charCount=str.length();
  for (int i=0; i < charCount; i++) {
    currentNode=currentNode.transition(str.charAt(i));
    if (equivalenceClassMDAGNodeHashMap.get(currentNode) == currentNode)     equivalenceClassMDAGNodeHashMap.remove(currentNode);
    if (currentNode != null)     currentNode.clearStoredHashCode();
  }
}
