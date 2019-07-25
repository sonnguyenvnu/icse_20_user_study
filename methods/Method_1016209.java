/** 
 * Repaints specified tree row.
 * @param row row index
 */
public void repaint(final int row){
  repaint(getWebUI().getRowBounds(row));
}
