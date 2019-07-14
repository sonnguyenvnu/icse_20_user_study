/** 
 * ????<br> Creates a space-saving version of the MDAG in the form of an array. Once the MDAG is simplified, Strings can no longer be added to or removed from it.
 */
public void simplify(){
  if (sourceNode != null) {
    mdagDataArray=new SimpleMDAGNode[transitionCount];
    createSimpleMDAGTransitionSet(sourceNode,mdagDataArray,0);
    simplifiedSourceNode=new SimpleMDAGNode('\0',false,sourceNode.getOutgoingTransitionCount());
    sourceNode=null;
    equivalenceClassMDAGNodeHashMap=null;
  }
}
