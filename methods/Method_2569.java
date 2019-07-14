/** 
 * ???????????????????<br> Performs minimization processing on a _transition path starting from a given node. <p/> This entails either replacing a node in the path with one that has an equivalent right language/equivalence class (defined as set of _transition paths that can be traversed and nodes able to be reached from it), or making it a representative of a right language/equivalence class if a such a node does not already exist.
 * @param originNode the MDAGNode that the _transition path corresponding to str starts from
 * @param str        a String related to a _transition path
 */
private void replaceOrRegister(MDAGNode originNode,String str){
  char transitionLabelChar=str.charAt(0);
  MDAGNode relevantTargetNode=originNode.transition(transitionLabelChar);
  if (relevantTargetNode.hasTransitions() && !str.substring(1).isEmpty())   replaceOrRegister(relevantTargetNode,str.substring(1));
  MDAGNode equivalentNode=equivalenceClassMDAGNodeHashMap.get(relevantTargetNode);
  if (equivalentNode == null)   equivalenceClassMDAGNodeHashMap.put(relevantTargetNode,relevantTargetNode);
 else   if (equivalentNode != relevantTargetNode) {
    relevantTargetNode.decrementTargetIncomingTransitionCounts();
    transitionCount-=relevantTargetNode.getOutgoingTransitionCount();
    originNode.reassignOutgoingTransition(transitionLabelChar,relevantTargetNode,equivalentNode);
  }
}
