/** 
 * Updates children positions. Takes cares about gravity and layout gravity. Finally layout children to parent if needed.
 * @param parentHeight parent parentHeight
 * @param totalSize total vertical size used by children in a column
 * @param column column number
 * @param maxChildWidth the biggest child width
 */
private void updateChildPositionVertical(int parentHeight,int totalSize,int column,int maxChildWidth){
  for (int i=0; i < mListPositions.size(); i++) {
    ViewPosition pos=mListPositions.get(i);
    final View child=getChildAt(i);
    if (mOrientation == HORIZONTAL || pos.position == column) {
      updateTopPositionByGravity(pos,parentHeight - totalSize,mGravity);
    }
    if (mOrientation == VERTICAL && pos.position == column) {
      LayoutParams lp=(LayoutParams)child.getLayoutParams();
      int size=maxChildWidth - child.getMeasuredWidth() - lp.leftMargin - lp.rightMargin;
      updateLeftPositionByGravity(pos,size,lp.gravity);
    }
    if (mOrientation == HORIZONTAL)     layout(child,pos);
  }
}
