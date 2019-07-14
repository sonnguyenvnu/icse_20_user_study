/** 
 * Gets the combined HTML contents of each element in the set of matched elements, including their descendants.
 * @see #html()
 * @param setIncluded if <code>true</code> than sets node are included in the output
 */
public String htmlAll(final boolean setIncluded){
  if (nodes.length == 0) {
    return StringPool.EMPTY;
  }
  StringBuilder sb=new StringBuilder();
  for (  Node node : nodes) {
    sb.append(setIncluded ? node.getHtml() : node.getInnerHtml());
  }
  return sb.toString();
}
