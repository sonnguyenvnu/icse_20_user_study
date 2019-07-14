private void addItemView(int position){
  int viewType=mAdapter.getItemViewType(position);
  RecyclerView.ViewHolder holder=mAdapter.createViewHolder(this,viewType);
  mAdapter.bindViewHolder(holder,position);
  View itemView=holder.itemView;
  itemView.setTag(holder);
  addView(itemView,position);
}
