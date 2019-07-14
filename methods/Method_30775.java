private void drawVertical(Canvas canvas,RecyclerView parent){
  canvas.save();
  int left;
  int right;
  if (parent.getClipToPadding()) {
    left=parent.getPaddingLeft();
    right=parent.getWidth() - parent.getPaddingRight();
    canvas.clipRect(left,parent.getPaddingTop(),right,parent.getHeight() - parent.getPaddingBottom());
  }
 else {
    left=0;
    right=parent.getWidth();
  }
  for (int i=0, count=parent.getChildCount(); i < count; ++i) {
    View child=parent.getChildAt(i);
    if (parent.getChildAdapterPosition(child) == 0) {
      continue;
    }
    parent.getDecoratedBoundsWithMargins(child,mBounds);
    int top=mBounds.top + Math.round(child.getTranslationY());
    int bottom=top + mDivider.getIntrinsicHeight();
    mDivider.setBounds(left,top,right,bottom);
    mDivider.draw(canvas);
  }
  canvas.restore();
}
