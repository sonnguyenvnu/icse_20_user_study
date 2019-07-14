private int getSpaceForChildren(boolean horizontal){
  if (horizontal) {
    return getTotalWidthPx(this) - getPaddingLeft() - (getClipToPadding() ? getPaddingRight() : 0);
  }
 else {
    return getTotalHeightPx(this) - getPaddingTop() - (getClipToPadding() ? getPaddingBottom() : 0);
  }
}
