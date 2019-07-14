/** 
 * Adds a String to the MDAG (called by addString to do actual MDAG manipulation).
 * @param str the String to be added to the MDAG
 */
private void addStringInternal(String str){
  String prefixString=determineLongestPrefixInMDAG(str);
  String suffixString=str.substring(prefixString.length());
  HashMap<String,Object> firstConfluenceNodeDataHashMap=getTransitionPathFirstConfluenceNodeData(sourceNode,prefixString);
  MDAGNode firstConfluenceNodeInPrefix=(MDAGNode)firstConfluenceNodeDataHashMap.get("confluenceNode");
  Integer toFirstConfluenceNodeTransitionCharIndex=(Integer)firstConfluenceNodeDataHashMap.get("toConfluenceNodeTransitionCharIndex");
  removeTransitionPathRegisterEntries((toFirstConfluenceNodeTransitionCharIndex == null ? prefixString : prefixString.substring(0,toFirstConfluenceNodeTransitionCharIndex)));
  if (firstConfluenceNodeInPrefix != null) {
    String transitionStringOfPathToFirstConfluenceNode=prefixString.substring(0,toFirstConfluenceNodeTransitionCharIndex + 1);
    String transitionStringOfToBeDuplicatedPath=prefixString.substring(toFirstConfluenceNodeTransitionCharIndex + 1);
    cloneTransitionPath(firstConfluenceNodeInPrefix,transitionStringOfPathToFirstConfluenceNode,transitionStringOfToBeDuplicatedPath);
  }
  addTransitionPath(sourceNode.transition(prefixString),suffixString);
}
