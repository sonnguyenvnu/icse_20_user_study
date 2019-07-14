public void onSearchClicked(){
  if (mFilterListener != null) {
    mFilterListener.onFilter(getFilters());
  }
  dismiss();
}
