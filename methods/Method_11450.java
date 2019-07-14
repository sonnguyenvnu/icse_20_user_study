@Override protected void drawItems(Canvas canvas){
  canvas.save();
  int w=getMeasuredWidth();
  int h=getMeasuredHeight();
  int iw=getItemDimension();
  mSpinBitmap.eraseColor(0);
  Canvas c=new Canvas(mSpinBitmap);
  Canvas cSpin=new Canvas(mSpinBitmap);
  int left=(mCurrentItemIdx - mFirstItemIdx) * iw + (iw - getWidth()) / 2;
  c.translate(-left + mScrollingOffset,mItemsPadding);
  mItemsLayout.draw(c);
  mSeparatorsBitmap.eraseColor(0);
  Canvas cSeparators=new Canvas(mSeparatorsBitmap);
  if (mSelectionDivider != null) {
    int leftOfLeftDivider=(getWidth() - iw - mSelectionDividerWidth) / 2;
    int rightOfLeftDivider=leftOfLeftDivider + mSelectionDividerWidth;
    mSelectionDivider.setBounds(leftOfLeftDivider,0,rightOfLeftDivider,getHeight());
    mSelectionDivider.draw(cSeparators);
    int leftOfRightDivider=leftOfLeftDivider + iw;
    int rightOfRightDivider=rightOfLeftDivider + iw;
    mSelectionDivider.setBounds(leftOfRightDivider,0,rightOfRightDivider,getHeight());
    mSelectionDivider.draw(cSeparators);
  }
  cSpin.drawRect(0,0,w,h,mSelectorWheelPaint);
  cSeparators.drawRect(0,0,w,h,mSeparatorsPaint);
  canvas.drawBitmap(mSpinBitmap,0,0,null);
  canvas.drawBitmap(mSeparatorsBitmap,0,0,null);
  canvas.restore();
}
