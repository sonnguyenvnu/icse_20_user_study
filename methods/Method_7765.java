@Override public boolean isEnabled(RecyclerView.ViewHolder holder){
  int type=holder.getItemViewType();
  return type != 1 && type != 3;
}
