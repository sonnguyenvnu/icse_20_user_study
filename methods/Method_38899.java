/** 
 * Returns CSS path to this node from document root.
 */
public String getCssPath(){
  StringBuilder path=new StringBuilder();
  Node node=this;
  while (node != null) {
    String nodeName=node.getNodeName();
    if (nodeName != null) {
      StringBuilder sb=new StringBuilder();
      sb.append(' ').append(nodeName);
      String id=node.getAttribute("id");
      if (id != null) {
        sb.append('#').append(id);
      }
      path.insert(0,sb);
    }
    node=node.getParentNode();
  }
  if (path.charAt(0) == ' ') {
    return path.substring(1);
  }
  return path.toString();
}
