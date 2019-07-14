private boolean useLeftPadding(){
  if (grid) {
    return (horizontallyScrolling && !isInFirstRow) || (verticallyScrolling && !isFirstItemInRow);
  }
  return horizontallyScrolling && !firstItem;
}
