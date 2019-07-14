public void setAlbumsCount(int count){
  for (int a=0; a < albumViews.length; a++) {
    albumViews[a].setVisibility(a < count ? VISIBLE : INVISIBLE);
  }
  albumsCount=count;
}
