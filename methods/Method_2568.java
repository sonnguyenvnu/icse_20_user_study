/** 
 * Determines the longest prefix of a given String that is the prefix of another String previously added to the MDAG.
 * @param str the String to be processed
 * @return a String of the longest prefix of {@code str}that is also a prefix of a String contained in the MDAG
 */
private String determineLongestPrefixInMDAG(String str){
  MDAGNode currentNode=sourceNode;
  int numberOfChars=str.length();
  int onePastPrefixEndIndex=0;
  for (int i=0; i < numberOfChars; i++, onePastPrefixEndIndex++) {
    char currentChar=str.charAt(i);
    if (currentNode.hasOutgoingTransition(currentChar))     currentNode=currentNode.transition(currentChar);
 else     break;
  }
  return str.substring(0,onePastPrefixEndIndex);
}
