@Override public boolean isEnabled(RecyclerView.ViewHolder holder){
  int viewType=holder.getItemViewType();
  return viewType != 1 && viewType != 5 && viewType != 3 && viewType != 8 && viewType != 7 && viewType != 9 && viewType != 10;
}
