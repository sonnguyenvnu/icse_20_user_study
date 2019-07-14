/** 
 * ???????????<br> Adds a _transition path starting from a specific node in the MDAG.
 * @param originNode the MDAGNode which will serve as the start point of the to-be-created _transition path
 * @param str        the String to be used to create a new _transition path from {@code originNode}
 */
private void addTransitionPath(MDAGNode originNode,String str){
  if (!str.isEmpty()) {
    MDAGNode currentNode=originNode;
    int charCount=str.length();
    for (int i=0; i < charCount; i++, transitionCount++) {
      char currentChar=str.charAt(i);
      boolean isLastChar=(i == charCount - 1);
      currentNode=currentNode.addOutgoingTransition(currentChar,isLastChar);
      charTreeSet.add(currentChar);
    }
  }
 else   originNode.setAcceptStateStatus(true);
}
