private void drawVertical(Canvas canvas,RecyclerView parent,RecyclerView.State state){
  canvas.save();
  final int left;
  final int right;
  if (parent.getClipToPadding()) {
    left=parent.getPaddingLeft();
    right=parent.getWidth() - parent.getPaddingRight();
    canvas.clipRect(left,parent.getPaddingTop(),right,parent.getHeight() - parent.getPaddingBottom());
  }
 else {
    left=0;
    right=parent.getWidth();
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
      parent.getDecoratedBoundsWithMargins(child,mBounds);
      final int bottom=mBounds.bottom + Math.round(child.getTranslationY());
      final int top=bottom - mDivider.getIntrinsicHeight();
      mDivider.setBounds(left,top,right,bottom);
      mDivider.draw(canvas);
    }
  }
  canvas.restore();
}
