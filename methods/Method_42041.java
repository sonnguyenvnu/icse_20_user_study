public void changeSortingOrder(SortingOrder sortingOrder){
  this.sortingOrder=sortingOrder;
  reverseOrder();
  notifyDataSetChanged();
}
