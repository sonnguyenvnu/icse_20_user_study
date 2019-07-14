private boolean useRightPadding(){
  if (grid) {
    return (horizontallyScrolling && !isInLastRow) || (verticallyScrolling && !fillsLastSpan);
  }
  return horizontallyScrolling && !lastItem;
}
