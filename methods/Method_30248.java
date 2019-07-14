private void bindItemCollectionListHolder(RecyclerView.ViewHolder holder,int position,SimpleItemCollection newItemCollection){
  ItemCollectionListHolder itemCollectionListHolder=(ItemCollectionListHolder)holder;
  ItemCollectionListAdapter adapter=(ItemCollectionListAdapter)itemCollectionListHolder.itemCollectionList.getAdapter();
  if (newItemCollection != null) {
    adapter.set(position,newItemCollection);
  }
 else {
    adapter.notifyItemChanged(position);
  }
}
