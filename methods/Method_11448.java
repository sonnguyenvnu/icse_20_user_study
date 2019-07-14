@Override protected void measureLayout(){
  mItemsLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
  mItemsLayout.measure(MeasureSpec.makeMeasureSpec(getWidth() + getItemDimension(),MeasureSpec.UNSPECIFIED),MeasureSpec.makeMeasureSpec(getHeight(),MeasureSpec.AT_MOST));
}
