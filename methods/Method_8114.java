public void setItemsCount(int count){
  for (int a=0; a < photoVideoViews.length; a++) {
    photoVideoViews[a].clearAnimation();
    photoVideoViews[a].setVisibility(a < count ? VISIBLE : INVISIBLE);
  }
  itemsCount=count;
}
