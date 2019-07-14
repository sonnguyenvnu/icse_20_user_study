private void splitTransitionPath(MDAGNode originNode,String storedStringSubstr){
  HashMap<String,Object> firstConfluenceNodeDataHashMap=getTransitionPathFirstConfluenceNodeData(originNode,storedStringSubstr);
  Integer toFirstConfluenceNodeTransitionCharIndex=(Integer)firstConfluenceNodeDataHashMap.get("toConfluenceNodeTransitionCharIndex");
  MDAGNode firstConfluenceNode=(MDAGNode)firstConfluenceNodeDataHashMap.get("confluenceNode");
  if (firstConfluenceNode != null) {
    MDAGNode firstConfluenceNodeParent=originNode.transition(storedStringSubstr.substring(0,toFirstConfluenceNodeTransitionCharIndex));
    MDAGNode firstConfluenceNodeClone=firstConfluenceNode.clone(firstConfluenceNodeParent,storedStringSubstr.charAt(toFirstConfluenceNodeTransitionCharIndex));
    transitionCount+=firstConfluenceNodeClone.getOutgoingTransitionCount();
    String unprocessedSubString=storedStringSubstr.substring(toFirstConfluenceNodeTransitionCharIndex + 1);
    splitTransitionPath(firstConfluenceNodeClone,unprocessedSubString);
  }
}
