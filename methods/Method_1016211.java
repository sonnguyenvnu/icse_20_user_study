/** 
 * Repaints specified node.
 * @param nodes nodes to repaint
 */
public void repaint(final List<E> nodes){
  if (nodes != null && nodes.size() > 0) {
    Rectangle summ=null;
    for (    final E node : nodes) {
      summ=GeometryUtils.getContainingRect(summ,getNodeBounds(node));
    }
    if (summ != null) {
      repaint(summ);
    }
  }
}
