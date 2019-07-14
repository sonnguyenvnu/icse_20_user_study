public void setItemCollectionListItem(int position,SimpleItemCollection newItemCollection){
  mDataAdapter.notifyItemCollectionListItemChanged(position,newItemCollection);
}
