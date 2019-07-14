private void adjustTextSize(){
  if (!initiallized) {
    return;
  }
  final int startSize=Math.round(minTextSize);
  final int heightLimit=getMeasuredHeight() - getCompoundPaddingBottom() - getCompoundPaddingTop();
  widthLimit=getMeasuredWidth() - getCompoundPaddingLeft() - getCompoundPaddingRight();
  if (widthLimit <= 0) {
    return;
  }
  availableSpaceRect.right=widthLimit;
  availableSpaceRect.bottom=heightLimit;
  super.setTextSize(TypedValue.COMPLEX_UNIT_PX,efficientTextSizeSearch(startSize,(int)maxTextSize,sizeTester,availableSpaceRect));
}
