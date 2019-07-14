@Override public void setMeasuredDimension(Rect childrenBounds,int wSpec,int hSpec){
  final int width, height;
  final int horizontalPadding=getPaddingLeft() + getPaddingRight();
  final int verticalPadding=getPaddingTop() + getPaddingBottom();
  if (mOrientation == VERTICAL) {
    final int usedHeight=childrenBounds.height() + verticalPadding;
    height=chooseSize(hSpec,usedHeight,getMinimumHeight());
    width=chooseSize(wSpec,mSizePerSpan * mSpanCount + horizontalPadding,getMinimumWidth());
  }
 else {
    final int usedWidth=childrenBounds.width() + horizontalPadding;
    width=chooseSize(wSpec,usedWidth,getMinimumWidth());
    height=chooseSize(hSpec,mSizePerSpan * mSpanCount + verticalPadding,getMinimumHeight());
  }
  setMeasuredDimension(width,height);
}
