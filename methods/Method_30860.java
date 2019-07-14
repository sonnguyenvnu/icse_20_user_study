private void init(View[] views,CharSequence[] titles){
  if (views.length != titles.length) {
    throw new IllegalArgumentException("View size and title size mismatch");
  }
  mViews=views;
  mTitles=titles;
}
