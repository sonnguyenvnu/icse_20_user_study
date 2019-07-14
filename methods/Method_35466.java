private void setRecyclerViewPosition(float y){
  if (recyclerView != null) {
    final int itemCount=recyclerView.getAdapter().getItemCount();
    float proportion;
    proportion=y / (float)(getHeight() - getPaddingTop());
    final int verticalScrollOffset=recyclerView.computeVerticalScrollOffset();
    final int verticalScrollRange=recyclerView.computeVerticalScrollRange() - recyclerView.getHeight() + recyclerView.getPaddingTop() + recyclerView.getPaddingBottom();
    float offset=verticalScrollRange * proportion - verticalScrollOffset + 0.5f;
    recyclerView.scrollBy(0,(int)offset);
    final int targetPos=getValueInRange(0,itemCount - 1,(int)(proportion * (float)itemCount));
    final String bubbleText=((BubbleTextGetter)recyclerView.getAdapter()).getTextToShowInBubble(targetPos);
    if (bubbleView != null)     bubbleView.setText(bubbleText);
  }
}
