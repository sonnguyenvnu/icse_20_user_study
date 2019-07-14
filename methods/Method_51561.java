/** 
 * Parse a String from a textually type node.
 * @param node The node.
 * @return The String.
 */
private static String parseTextNode(Node node){
  final int nodeCount=node.getChildNodes().getLength();
  if (nodeCount == 0) {
    return "";
  }
  StringBuilder buffer=new StringBuilder();
  for (int i=0; i < nodeCount; i++) {
    Node childNode=node.getChildNodes().item(i);
    if (childNode.getNodeType() == Node.CDATA_SECTION_NODE || childNode.getNodeType() == Node.TEXT_NODE) {
      buffer.append(childNode.getNodeValue());
    }
  }
  return buffer.toString();
}
