public void changeSortingOrder(SortingOrder sortingOrder){
  this.sortingOrder=sortingOrder;
  Collections.reverse(media);
  notifyDataSetChanged();
}
