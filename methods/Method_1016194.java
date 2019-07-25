/** 
 * Repaints all list cells between the specified indices.
 * @param from first cell index
 * @param to   last cell index
 */
public void repaint(final int from,final int to){
  final Rectangle cellBounds=getCellBounds(from,to);
  if (cellBounds != null) {
    repaint(cellBounds);
  }
}
