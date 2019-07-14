private void scrollToField(View field){
  while (field != null && linearLayout2.indexOfChild(field) < 0) {
    field=(View)field.getParent();
  }
  if (field != null) {
    scrollView.smoothScrollTo(0,field.getTop() - (scrollView.getMeasuredHeight() - field.getMeasuredHeight()) / 2);
  }
}
