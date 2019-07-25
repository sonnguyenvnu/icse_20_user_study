private void clear(View... views){
  if (null == views) {
    return;
  }
  try {
    for (    View view : views) {
      if (null != view) {
        removeView(view);
      }
    }
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
}
