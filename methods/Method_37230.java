private View getViewFromRecycler(@NonNull BaseCell cell){
  GroupBasicAdapter adapter=cell.serviceManager.getService(GroupBasicAdapter.class);
  RecyclerView.RecycledViewPool pool=cell.serviceManager.getService(RecyclerView.RecycledViewPool.class);
  int itemViewType=adapter.getItemType(cell);
  BinderViewHolder holder=(BinderViewHolder)pool.getRecycledView(itemViewType);
  if (holder == null) {
    holder=(BinderViewHolder)adapter.createViewHolder(this,itemViewType);
  }
  holder.bind(cell);
  mViewHolders.add(holder);
  return holder.itemView;
}
