private boolean useTopPadding(){
  if (grid) {
    return (horizontallyScrolling && !isFirstItemInRow) || (verticallyScrolling && !isInFirstRow);
  }
  return verticallyScrolling && !firstItem;
}
