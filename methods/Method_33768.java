private void drawHorizontal(Canvas canvas,RecyclerView parent,RecyclerView.State state){
  canvas.save();
  final int top;
  final int bottom;
  if (parent.getClipToPadding()) {
    top=parent.getPaddingTop();
    bottom=parent.getHeight() - parent.getPaddingBottom();
    canvas.clipRect(parent.getPaddingLeft(),top,parent.getWidth() - parent.getPaddingRight(),bottom);
  }
 else {
    top=0;
    bottom=parent.getHeight();
  }
  final int childCount=parent.getChildCount();
  final int lastPosition=state.getItemCount() - 1;
  for (int i=0; i < childCount; i++) {
    final View child=parent.getChildAt(i);
    final int childRealPosition=parent.getChildAdapterPosition(child);
    if (childRealPosition == 0 && !mIsShowFirstDivider) {
      continue;
    }
    if (childRealPosition == 1 && !mIsShowSecondDivider) {
      continue;
    }
    if (mIsShowBottomDivider || childRealPosition < lastPosition) {
      parent.getLayoutManager().getDecoratedBoundsWithMargins(child,mBounds);
      final int right=mBounds.right + Math.round(child.getTranslationX());
      final int left=right - mDivider.getIntrinsicWidth();
      mDivider.setBounds(left,top,right,bottom);
      mDivider.draw(canvas);
    }
  }
  canvas.restore();
}
