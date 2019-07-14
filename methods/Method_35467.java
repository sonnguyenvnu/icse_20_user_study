private void updateBubbleAndHandlePosition(){
  if (recyclerView == null || bubbleView == null || fastScroll.isSelected())   return;
  final int verticalScrollOffset=recyclerView.computeVerticalScrollOffset();
  final int verticalScrollRange=recyclerView.computeVerticalScrollRange();
  float proportion=(float)verticalScrollOffset / ((float)verticalScrollRange - getHeight());
  setBubbleAndHandlePosition(getHeight() * proportion);
}
