/** 
 * Removes a String from the MDAG.
 * @param str the String to be removed from the MDAG
 */
public void removeString(String str){
  if (sourceNode != null) {
    splitTransitionPath(sourceNode,str);
    removeTransitionPathRegisterEntries(str);
    MDAGNode strEndNode=sourceNode.transition(str);
    if (strEndNode == null)     return;
    if (!strEndNode.hasTransitions()) {
      int soleInternalTransitionPathLength=calculateSoleTransitionPathLength(str);
      int internalTransitionPathLength=str.length() - 1;
      if (soleInternalTransitionPathLength == internalTransitionPathLength) {
        sourceNode.removeOutgoingTransition(str.charAt(0));
        transitionCount-=str.length();
      }
 else {
        int toBeRemovedTransitionLabelCharIndex=(internalTransitionPathLength - soleInternalTransitionPathLength);
        MDAGNode latestNonSoloTransitionPathNode=sourceNode.transition(str.substring(0,toBeRemovedTransitionLabelCharIndex));
        latestNonSoloTransitionPathNode.removeOutgoingTransition(str.charAt(toBeRemovedTransitionLabelCharIndex));
        transitionCount-=str.substring(toBeRemovedTransitionLabelCharIndex).length();
        replaceOrRegister(sourceNode,str.substring(0,toBeRemovedTransitionLabelCharIndex));
      }
    }
 else {
      strEndNode.setAcceptStateStatus(false);
      replaceOrRegister(sourceNode,str);
    }
  }
 else {
    unSimplify();
  }
}
