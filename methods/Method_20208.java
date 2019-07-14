private boolean useBottomPadding(){
  if (grid) {
    return (horizontallyScrolling && !fillsLastSpan) || (verticallyScrolling && !isInLastRow);
  }
  return verticallyScrolling && !lastItem;
}
