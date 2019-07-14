/** 
 * Returns the text content of this node and its descendants.
 * @see #appendTextContent(Appendable)
 */
public String getTextContent(){
  StringBuilder sb=new StringBuilder(getChildNodesCount() + 1);
  appendTextContent(sb);
  return sb.toString();
}
