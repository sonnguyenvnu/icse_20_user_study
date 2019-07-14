/** 
 * Draws the divider on the canvas provided by RecyclerView Be advised - divider will be drawn before the views, hence it'll be below the views of adapter
 */
private void drawVertical(Canvas c,RecyclerView parent){
  final int left=parent.getPaddingLeft() + leftPaddingPx;
  final int right=parent.getWidth() - parent.getPaddingRight() - rightPaddingPx;
  final int childCount=parent.getChildCount();
  for (int i=showtopbottomdividers ? 0 : 1; i < childCount - 1; i++) {
    final View child=parent.getChildAt(i);
    int viewType=parent.getChildViewHolder(child).getItemViewType();
    if (viewType == RecyclerAdapter.TYPE_HEADER_FILES || viewType == RecyclerAdapter.TYPE_HEADER_FOLDERS) {
      continue;
    }
    final RecyclerView.LayoutParams params=(RecyclerView.LayoutParams)child.getLayoutParams();
    final int top=child.getBottom() + params.bottomMargin;
    final int bottom=top + mDivider.getIntrinsicHeight();
    mDivider.setBounds(left,top,right,bottom);
    mDivider.draw(c);
  }
}
