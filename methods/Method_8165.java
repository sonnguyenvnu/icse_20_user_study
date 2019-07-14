public void setParams(int columns,boolean top,boolean bottom){
  spanCount=columns;
  isTop=top;
  isBottom=bottom;
  for (int a=0; a < wallpaperViews.length; a++) {
    wallpaperViews[a].setVisibility(a < columns ? VISIBLE : GONE);
    wallpaperViews[a].clearAnimation();
  }
}
