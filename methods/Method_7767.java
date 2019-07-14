@Override public boolean isEnabled(RecyclerView.ViewHolder holder){
  int itemType=holder.getItemViewType();
  return itemType == 3 || itemType == 4 || itemType == 5;
}
