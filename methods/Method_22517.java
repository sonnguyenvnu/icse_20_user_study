/** 
 * Amount to scroll to reveal the rest of something we are on or a new item
 */
@Override public int getScrollableUnitIncrement(Rectangle visibleRect,int orientation,int direction){
  if (orientation != SwingConstants.VERTICAL) {
    return 0;
  }
  int lastHeight=0;
  int height=0;
  int bottomOfScrollArea=visibleRect.y + visibleRect.height;
  for (  Component c : getComponents()) {
    if (!(c.isVisible() && c instanceof DetailPanel)) {
      continue;
    }
    Dimension d=c.getPreferredSize();
    int nextHeight=height + d.height;
    if (direction > 0) {
      if (nextHeight > bottomOfScrollArea) {
        return nextHeight - bottomOfScrollArea;
      }
    }
 else     if (nextHeight > visibleRect.y) {
      if (visibleRect.y != height) {
        return visibleRect.y - height;
      }
 else {
        return visibleRect.y - lastHeight;
      }
    }
    lastHeight=height;
    height=nextHeight;
  }
  return 0;
}
