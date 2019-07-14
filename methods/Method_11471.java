@Override protected void drawItems(Canvas canvas){
  canvas.save();
  int w=getMeasuredWidth();
  int h=getMeasuredHeight();
  int ih=getItemDimension();
  mSpinBitmap.eraseColor(0);
  Canvas c=new Canvas(mSpinBitmap);
  Canvas cSpin=new Canvas(mSpinBitmap);
  int top=(mCurrentItemIdx - mFirstItemIdx) * ih + (ih - getHeight()) / 2;
  c.translate(mItemsPadding,-top + mScrollingOffset);
  mItemsLayout.draw(c);
  mSeparatorsBitmap.eraseColor(0);
  Canvas cSeparators=new Canvas(mSeparatorsBitmap);
  if (mSelectionDivider != null) {
    int topOfTopDivider=(getHeight() - ih - mSelectionDividerHeight) / 2;
    int bottomOfTopDivider=topOfTopDivider + mSelectionDividerHeight;
    mSelectionDivider.setBounds(0,topOfTopDivider,w,bottomOfTopDivider);
    mSelectionDivider.draw(cSeparators);
    int topOfBottomDivider=topOfTopDivider + ih;
    int bottomOfBottomDivider=bottomOfTopDivider + ih;
    mSelectionDivider.setBounds(0,topOfBottomDivider,w,bottomOfBottomDivider);
    mSelectionDivider.draw(cSeparators);
  }
  cSpin.drawRect(0,0,w,h,mSelectorWheelPaint);
  cSeparators.drawRect(0,0,w,h,mSeparatorsPaint);
  canvas.drawBitmap(mSpinBitmap,0,0,null);
  canvas.drawBitmap(mSeparatorsBitmap,0,0,null);
  canvas.restore();
}
