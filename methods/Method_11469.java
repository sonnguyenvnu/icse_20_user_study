@Override protected void measureLayout(){
  mItemsLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
  mItemsLayout.measure(MeasureSpec.makeMeasureSpec(getWidth() - 2 * mItemsPadding,MeasureSpec.EXACTLY),MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED));
}
