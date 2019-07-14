/** 
 * Gets the combined text contents of each element in the set of matched elements, including their descendants. Text is HTML decoded for text nodes.
 */
public String text(){
  if (nodes.length == 0) {
    return StringPool.EMPTY;
  }
  StringBuilder sb=new StringBuilder();
  for (  Node node : nodes) {
    sb.append(node.getTextContent());
  }
  return sb.toString();
}
