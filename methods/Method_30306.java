@Override public void onItemCollectionListItemChanged(int requestCode,int position,SimpleItemCollection newItemCollection){
  mAdapter.setItemCollectionListItem(position,newItemCollection);
}
