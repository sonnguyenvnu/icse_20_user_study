private void updateItemView(int position){
  View itemView=getChildAt(position);
  RecyclerView.ViewHolder holder=(RecyclerView.ViewHolder)itemView.getTag();
  mAdapter.bindViewHolder(holder,position);
}
