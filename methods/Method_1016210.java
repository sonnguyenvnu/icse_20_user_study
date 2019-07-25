/** 
 * Repaints specified node.
 * @param node node to repaint
 */
public void repaint(final E node){
  if (node != null) {
    repaint(getNodeBounds(node));
  }
}
