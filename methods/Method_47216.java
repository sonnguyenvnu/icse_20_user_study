private void sendScroll(final HorizontalScrollView scrollView){
  new Handler().postDelayed(() -> scrollView.fullScroll(View.FOCUS_RIGHT),100);
}
