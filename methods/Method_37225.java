/** 
 * Find the first completely visible position.
 * @param starts A recorder array to save each item's left position, including its margin.
 * @return Position of first completely visible item.
 */
private int computeFirstCompletelyVisibleItemPositionForScrolledX(float[] starts){
  if (lSCell == null || starts == null || starts.length <= 0) {
    return 0;
  }
  for (int i=0; i < starts.length; i++) {
    if (starts[i] >= lSCell.currentDistance) {
      return i;
    }
  }
  return starts.length - 1;
}
