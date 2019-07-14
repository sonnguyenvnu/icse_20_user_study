public void setupFor(Album album){
  media.clear();
  changeSortingMode(album.settings.getSortingMode());
  changeSortingOrder(album.settings.getSortingOrder());
  notifyDataSetChanged();
}
