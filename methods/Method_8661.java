private void checkLayout(){
  if (itemSpans.size() != getFlowItemCount() || calculatedWidth != getWidth()) {
    calculatedWidth=getWidth();
    prepareLayout(getWidth());
  }
}
