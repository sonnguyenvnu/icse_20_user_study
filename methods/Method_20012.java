public void onClearFilterClicked(){
  mFilterDialog.resetFilters();
  onFilter(Filters.getDefault());
}
