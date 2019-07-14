/** 
 * Gets the position of the view that is most prominently displayed within the list view.
 */
public int getMostVisiblePosition(){
  final int firstPosition=getFirstVisiblePosition();
  final int height=getHeight();
  int maxDisplayedHeight=0;
  int mostVisibleIndex=0;
  int i=0;
  int bottom=0;
  while (bottom < height) {
    View child=getChildAt(i);
    if (child == null) {
      break;
    }
    bottom=child.getBottom();
    int displayedHeight=Math.min(bottom,height) - Math.max(0,child.getTop());
    if (displayedHeight > maxDisplayedHeight) {
      mostVisibleIndex=i;
      maxDisplayedHeight=displayedHeight;
    }
    i++;
  }
  return firstPosition + mostVisibleIndex;
}
