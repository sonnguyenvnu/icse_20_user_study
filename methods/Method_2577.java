/** 
 * ????<br> Determines whether a String is present in the MDAG.
 * @param str the String to be searched for
 * @return true if {@code str} is present in the MDAG, and false otherwise
 */
public boolean contains(String str){
  if (sourceNode != null) {
    MDAGNode targetNode=sourceNode.transition(str.toCharArray());
    return (targetNode != null && targetNode.isAcceptNode());
  }
 else {
    SimpleMDAGNode targetNode=simplifiedSourceNode.transition(mdagDataArray,str.toCharArray());
    return (targetNode != null && targetNode.isAcceptNode());
  }
}
