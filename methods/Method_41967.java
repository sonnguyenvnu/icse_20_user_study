@Override public void onItemsSelected(int count,int total){
  toolbar.setTitle(getString(R.string.toolbar_selection_count,count,total));
}
