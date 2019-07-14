public void notifyItemCollectionChanged(){
  int position=Items.ITEM_COLLECTION.ordinal();
  if (position < getItemCount()) {
    notifyItemChanged(position);
  }
}
