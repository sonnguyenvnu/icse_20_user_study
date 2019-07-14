/** 
 * Gets the value of the node at the given path relative to the given base element. For element nodes the value is the text content and for the attributes node the attribute value.
 * @param base base element
 * @param path path
 * @return the node value or null if no such node was found
 */
public static String getNodeValue(Element base,Path path){
  Node node=getElementNode(base,path);
  if (node != null) {
    if (path.attribute != null) {
      Node at=node.getAttributes().getNamedItem(path.attribute);
      return at != null ? at.getNodeValue() : null;
    }
 else {
      return node.getTextContent();
    }
  }
  return null;
}
