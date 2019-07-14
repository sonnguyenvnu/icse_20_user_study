@Override protected void onScrollTouchedUp(){
  super.onScrollTouchedUp();
  int cnt=mItemsLayout.getChildCount();
  View itm;
  Log.e(LOG_TAG," ----- layout: " + mItemsLayout.getMeasuredWidth() + mItemsLayout.getMeasuredHeight());
  Log.e(LOG_TAG," -------- dumping " + cnt + " items");
  for (int i=0; i < cnt; i++) {
    itm=mItemsLayout.getChildAt(i);
    Log.e(LOG_TAG," item #" + i + ": " + itm.getWidth() + "x" + itm.getHeight());
    itm.forceLayout();
  }
  Log.e(LOG_TAG," ---------- dumping finished ");
}
