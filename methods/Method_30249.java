protected void notifyItemCollectionListItemChanged(int position,int itemCollectionPosition,SimpleItemCollection newItemCollection){
  if (position < getItemCount()) {
    notifyItemChanged(position,Arrays.asList(itemCollectionPosition,newItemCollection));
  }
}
