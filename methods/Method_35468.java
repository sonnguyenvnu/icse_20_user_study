private void setBubbleAndHandlePosition(float y){
  final int handleHeight=fastScroll.getHeight();
  fastScroll.setY(getValueInRange(getPaddingTop(),getHeight() - handleHeight - getPaddingBottom(),(int)(y - handleHeight / 2)));
  if (bubbleView != null) {
    int bubbleHeight=bubbleView.getHeight();
    bubbleView.setY(getValueInRange(getPaddingTop(),getHeight() - bubbleHeight - handleHeight / 2 - getPaddingBottom(),(int)(y - bubbleHeight)));
  }
}
