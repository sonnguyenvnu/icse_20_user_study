/** 
 * ????????<br> Follows a _transition path starting from this node.
 * @param str               a String corresponding a _transition path in the MDAG
 * @return                  the MDAGNode at the end of the _transition path corresponding to{@code str}, or null if such a _transition path is not present in the MDAG
 */
public MDAGNode transition(String str){
  int charCount=str.length();
  MDAGNode currentNode=this;
  for (int i=0; i < charCount; i++) {
    currentNode=currentNode.transition(str.charAt(i));
    if (currentNode == null)     break;
  }
  return currentNode;
}
